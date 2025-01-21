package com.codebox.submission_service.service;

import com.codebox.submission_service.model.Submission;
import com.codebox.submission_service.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubmissionService {

  @Autowired
  private SubmissionRepository submissionRepository;

  public Submission save(Submission submission) {
    return submissionRepository.save(submission);
  }

  public Optional<Submission> getSubmissionById(String id) {
    return submissionRepository.findById(id);
  }

}
