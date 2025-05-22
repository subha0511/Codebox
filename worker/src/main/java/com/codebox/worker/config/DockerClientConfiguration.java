package com.codebox.worker.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class DockerClientConfiguration {

  @Autowired
  DockerConfigurationProperites dockerConfigurationProperites;

  @Bean
  public com.github.dockerjava.core.DockerClientConfig dockerClientConfig() {
    return DefaultDockerClientConfig.createDefaultConfigBuilder()
        .withDockerHost(dockerConfigurationProperites.getHost())
        .withDockerTlsVerify(dockerConfigurationProperites.getTlsVerify())
        .build();
  }

  @Bean
  public ApacheDockerHttpClient dockerHttpClient(
      com.github.dockerjava.core.DockerClientConfig dockerClientConfig) {
    return new ApacheDockerHttpClient.Builder().dockerHost(dockerClientConfig.getDockerHost())
        .connectionTimeout(Duration.ofSeconds(10))
        .responseTimeout(Duration.ofSeconds(30))
        .build();
  }

  @Bean
  public DockerClient dockerClient(com.github.dockerjava.core.DockerClientConfig config, DockerHttpClient client) {
    return DockerClientImpl.getInstance(config, client);
  }

}