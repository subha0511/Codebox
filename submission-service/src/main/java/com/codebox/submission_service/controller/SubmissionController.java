package com.codebox.submission_service.controller;

import com.codebox.submission_service.client.ProblemClient;
import com.codebox.submission_service.dto.SubmissionDTO;
import com.codebox.submission_service.mapper.impl.SubmissionDetailMapper;
import com.codebox.submission_service.mapper.impl.SubmissionMapper;
import com.codebox.submission_service.mapper.impl.TestcaseMapper;
import com.codebox.submission_service.model.Submission;
import com.codebox.submission_service.pojo.Problem;
import com.codebox.submission_service.service.SubmissionService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/submission")
public class SubmissionController {

  @Autowired
  private SubmissionService submissionService;

  @Autowired
  private SubmissionMapper submissionMapper;

  @Autowired
  private SubmissionDetailMapper submissionDetailMapper;

  @Autowired
  private TestcaseMapper testcaseMapper;

  @Autowired
  private ProblemClient problemClient;

  @GetMapping(path = "/{id}")
  public ResponseEntity<?> getSubmissionById(@NotNull @PathVariable("id") String id) {
    Optional<Submission> submission = submissionService.getSubmissionById(id);
    if (submission.isEmpty()) {
      return new ResponseEntity<>(Map.of("message", "Submission Not Found"), HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(submissionMapper.mapTo(submission.get()));
  }

  @PostMapping(path = "/")
  public ResponseEntity<?> createSubmission(@Valid @RequestBody SubmissionDTO submissionDTO) {
    Optional<Problem> problem = problemClient.getProblemById(submissionDTO.getProblemId());

    if (problem.isEmpty()) {
      return new ResponseEntity<>(Map.of("message", "Problem Not Found"), HttpStatus.NOT_FOUND);
    }

    Submission submission = submissionMapper.mapFrom(submissionDTO);
    submission.setSubmissionTestcases(
        problem.get().getTestcases().stream().map(testcaseMapper::mapTo).toList());
    Submission savedSubmission = submissionService.save(submission);
    return new ResponseEntity<>(submissionMapper.mapTo(savedSubmission), HttpStatus.CREATED);
  }

}
