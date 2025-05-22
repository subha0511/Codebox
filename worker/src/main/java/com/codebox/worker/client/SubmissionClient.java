package com.codebox.worker.client;

import com.codebox.worker.domain.dto.SubmissionDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/api/internal/submission")
public interface SubmissionClient {

  @PostExchange("/{id}")
  void updateSubmission(@PathVariable("id") String id, @RequestBody SubmissionDTO submissionDTO);
}