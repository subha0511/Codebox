package com.codebox.worker.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Getter
@Component
@ConfigurationProperties(prefix = "docker")
public class DockerConfigurationProperites {

  private final Pool pool = new Pool();

  @Setter
  private String host;

  @Setter
  private Boolean tlsVerify;

  @Getter
  @Setter
  public static class Pool {
    private int size;
    private String imageName;
  }
}