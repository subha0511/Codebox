package com.codebox.submission_service.controller;

import com.codebox.shared_dtos.domain.problem.ProblemDetailDTO;
import com.codebox.submission_service.client.ProblemClient;
import com.codebox.submission_service.dto.SubmissionDTO;
import com.codebox.submission_service.mapper.impl.ProblemTestcaseMapper;
import com.codebox.submission_service.mapper.impl.SubmissionDetailMapper;
import com.codebox.submission_service.mapper.impl.SubmissionMapper;
import com.codebox.submission_service.model.Submission;
import com.codebox.submission_service.service.SubmissionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submission")
public class SubmissionController {

    @Autowired
    private ProblemClient problemClient;

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private SubmissionMapper submissionMapper;

    @Autowired
    private SubmissionDetailMapper submissionDetailMapper;

    @Autowired
    private ProblemTestcaseMapper problemTestcaseMapper;

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
        Optional<ProblemDetailDTO> problem = problemClient.getProblemById(submissionDTO.getProblemId());

        if (problem.isEmpty()) {
            return new ResponseEntity<>(Map.of("message", "Problem Not Found"), HttpStatus.NOT_FOUND);
        }

        Submission submission = submissionMapper.mapFrom(submissionDTO);
        submission.setSubmissionTestcases(problem.get().getTestcases().stream()
                .map(problemTestcaseMapper::mapTo)
                .toList());
        Submission savedSubmission = submissionService.save(submission);
        return new ResponseEntity<>(submissionMapper.mapTo(savedSubmission), HttpStatus.CREATED);
    }
}
