package com.codebox.submission_service.config;

import com.codebox.submission_service.client.ProblemClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

  @Value("${problem-service.url}")
  private String problemServiceUrl;

  @Bean
  public ProblemClient problemClient() {
    RestClient restClient = RestClient.builder().baseUrl(problemServiceUrl).build();
    RestClientAdapter adapter = RestClientAdapter.create(restClient);
    HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
    return factory.createClient(ProblemClient.class);
  }

}
