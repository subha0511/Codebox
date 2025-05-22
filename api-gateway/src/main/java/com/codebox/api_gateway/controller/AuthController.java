package com.codebox.api_gateway.controller;

import com.codebox.api_gateway.config.KeycloakConfigurationProperties;
import com.codebox.api_gateway.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private KeycloakConfigurationProperties keycloakConfig;

  @Autowired
  private AuthService authService;

  @GetMapping("/hello")
  public ResponseEntity<String> hello() {
    return ResponseEntity.ok("Hello from API Gateway");
  }

  @GetMapping("/login")
  public ResponseEntity<Void> login(HttpServletResponse response) {
    String codeVerifier = authService.generateCodeVerifier();
    String codeChallenge = authService.generateCodeChallenge(codeVerifier);

    Cookie cookie = new Cookie("code_verifier", codeVerifier);
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    cookie.setPath("/api/auth");
    response.addCookie(cookie);

    String authUrl = keycloakConfig.getAuthServerUrl() + "?client_id=" + keycloakConfig.getClient()
        .getId() + "&response_type=code" + "&redirect_uri=" + keycloakConfig.getRedirectUri()
        + "&scope=" + keycloakConfig.getScope() + "&code_challenge=" + codeChallenge
        + "&code_challenge_method=S256" + "&identity_provider=google"; // Trigger Google IdP

    System.out.println(authUrl);

    return ResponseEntity.status(HttpStatus.FOUND)
        .location(URI.create(authUrl))
        .build();
  }

  @GetMapping("/callback")
  public ResponseEntity<Map<String, String>> callback(
      @RequestParam("code") String code, @CookieValue("code_verifier") String codeVerifier,
      HttpServletResponse response) {

    Map<String, String> tokens = authService.handleCallback(code, codeVerifier);

    Cookie cookie = new Cookie("refresh_token", tokens.get("refreshToken"));
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    cookie.setPath("/api/auth");
    response.addCookie(cookie);

    return ResponseEntity.status(HttpStatus.FOUND)
        .body(Map.of("access_token", tokens.get("refreshToken")));
  }

  @PostMapping("/refresh")
  public ResponseEntity<Map<String, String>> refreshToken(
      @CookieValue("refresh_token") String refreshToken, HttpServletResponse response) {

    Map<String, String> tokens = authService.refreshToken(refreshToken);

    Cookie cookie = new Cookie("refresh_token", tokens.get("refreshToken"));
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    cookie.setPath("/api/auth");
    response.addCookie(cookie);

    return ResponseEntity.ok()
        .body(Map.of("access_token", tokens.get("accessToken")));
  }
}