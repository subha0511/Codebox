package com.codebox.submission_service.controller;

import com.codebox.submission_service.domain.model.Submission;
import com.codebox.submission_service.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal/submission")
public class InternalSubmissionController {

  @Autowired
  SubmissionService submissionService;

  @PostMapping("/{id}")
  public void updateSubmission(@PathVariable("id") String id, @RequestBody Submission submission) {
    submissionService.updateSubmission(submission);
  }
}
