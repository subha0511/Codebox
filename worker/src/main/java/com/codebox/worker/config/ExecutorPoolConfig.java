package com.codebox.worker.config;

import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorPoolConfig {

  @Autowired
  DockerConfigurationProperites dockerConfigurationProperites;

  private ExecutorService executorService;

  @Bean
  @Qualifier("TestcaseExecutorPool")
  public ExecutorService executorService() {
    executorService = Executors.newFixedThreadPool(dockerConfigurationProperites.getPool()
        .getSize());
    return executorService;
  }

  @PreDestroy
  public void destroy() {
    executorService.shutdown();
  }

}
