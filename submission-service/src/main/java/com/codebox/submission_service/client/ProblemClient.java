package com.codebox.submission_service.client;

import com.codebox.shared_dtos.domain.problem.ProblemDetailDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface ProblemClient {

    @GetExchange("/api/internal-problem/{id}")
    @CircuitBreaker(name = "problem-service", fallbackMethod = "fallbackGetDefaultProblemById")
    @Retry(name = "problem-service")
    Optional<ProblemDetailDTO> getProblemById(@PathVariable("id") long id);

    default Optional<ProblemDetailDTO> fallbackGetDefaultProblemById(long id, Throwable throwable) {
        return Optional.empty();
    }
}
