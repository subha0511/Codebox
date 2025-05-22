package com.codebox.api_gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("service")
@Data
public class ServiceConfigurationProperties {

  private String problemServiceUrl;
  private String submissionServiceUrl;
  private String contestServiceUrl;
}
