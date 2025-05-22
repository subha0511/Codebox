package com.codebox.worker.config;

import com.codebox.worker.client.SubmissionClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    @Value("${submission-service.url}")
    private String submissionServiceUrl;

    @Bean
    public SubmissionClient submissionClient() {
        RestClient restClient = RestClient.builder().baseUrl(submissionServiceUrl).build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(SubmissionClient.class);
    }
}
