package com.codebox.worker.controller;

import com.codebox.worker.domain.dto.SubmissionDTO;
import com.codebox.worker.service.TestcaseExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/worker")
public class DummySubmissionController {

  @Autowired
  TestcaseExecutorService testcaseExecutorService;

  @PostMapping("/submit")
  public Map<String, Object> submit(@RequestBody SubmissionDTO submissionDTO) {
    SubmissionDTO output = testcaseExecutorService.processSubmission(submissionDTO);
    return Map.of("Value", "Submission successful", "Output", output);
  }
}
