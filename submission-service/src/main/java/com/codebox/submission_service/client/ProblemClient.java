package com.codebox.submission_service.client;

import com.codebox.submission_service.pojo.Problem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.Optional;

public interface ProblemClient {

  @GetExchange("/api/internal-problem/{id}")
  Optional<Problem> getProblemById(@PathVariable long id);

}
