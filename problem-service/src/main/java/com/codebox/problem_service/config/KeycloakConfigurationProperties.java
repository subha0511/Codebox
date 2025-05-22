package com.codebox.problem_service.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("keycloak")
@Data
public class KeycloakConfigurationProperties {

  private String authServerUrl;
  private String redirectUri;
  private String tokenUri;
  private String certificateUri;
  private String scope;
  private Client client;

  @Data
  public static class Client {
    private String id;
    private String secret;
  }

}
