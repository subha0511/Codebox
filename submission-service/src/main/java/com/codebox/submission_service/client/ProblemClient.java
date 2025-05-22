package com.codebox.submission_service.client;

import com.codebox.submission_service.domain.dto.ProblemDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface ProblemClient {

    @GetExchange("/api/internal/problem/{id}")
    @CircuitBreaker(name = "problem-service")
    @Retry(name = "problem-service")
    Optional<ProblemDTO> getProblemById(@PathVariable("id") long id);

}
