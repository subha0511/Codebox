package com.codebox.api_gateway.service;

import com.codebox.api_gateway.config.KeycloakConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;

@Service
public class AuthService {

  @Autowired
  private KeycloakConfigurationProperties keycloakConfig;

  public Map<String, String> handleCallback(String code, String codeVerifier) {

    RestTemplate restTemplate = new RestTemplate();
    MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
    requestBody.add("grant_type", "authorization_code");
    requestBody.add(
        "client_id", keycloakConfig.getClient()
            .getId());
    requestBody.add(
        "client_secret", keycloakConfig.getClient()
            .getSecret());
    requestBody.add("code", code);
    requestBody.add("redirect_uri", keycloakConfig.getRedirectUri());
    requestBody.add("code_verifier", codeVerifier);

    ResponseEntity<Map> tokenResponse = restTemplate.postForEntity(
        keycloakConfig.getTokenUri(),
        requestBody, Map.class);

    Map<String, Object> tokens = tokenResponse.getBody();

    String accessToken = (String) tokens.get("access_token");
    String refreshToken = (String) tokens.get("refresh_token");

    return Map.of("access_token", accessToken, "refresh_token", refreshToken);
  }

  public Map<String, String> refreshToken(
      String refreshToken) {
    MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
    formData.add(
        "client_id", keycloakConfig.getClient()
            .getId());
    formData.add("grant_type", "refresh_token");
    formData.add("refresh_token", refreshToken);

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Map> tokenResponse = restTemplate.postForEntity(
        keycloakConfig.getTokenUri(),
        formData, Map.class);

    Map tokens = tokenResponse.getBody();

    String accessToken = (String) tokens.get("access_token");
    String newRefreshToken = (String) tokens.get("refresh_token");

    return Map.of("access_token", accessToken, "refresh_token", newRefreshToken);
  }


  public String generateCodeVerifier() {
    SecureRandom sr = new SecureRandom();
    byte[] code = new byte[32];
    sr.nextBytes(code);
    return Base64.getUrlEncoder()
        .withoutPadding()
        .encodeToString(code);
  }

  public String generateCodeChallenge(String codeVerifier) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      byte[] digested = md.digest(codeVerifier.getBytes(StandardCharsets.UTF_8));
      return Base64.getUrlEncoder()
          .withoutPadding()
          .encodeToString(digested);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Failed to generate code challenge", e);
    }
  }
}
