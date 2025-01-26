package com.codebox.submission_service.service;

import com.codebox.shared_dtos.schema.SubmissionSchema;
import com.codebox.submission_service.mapper.impl.SubmissionSchemaMapper;
import com.codebox.submission_service.model.Submission;
import com.codebox.submission_service.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubmissionService {

  @Autowired
  private SubmissionRepository submissionRepository;

  @Autowired
  private KafkaTemplate<String, SubmissionSchema> problemSubmissionKafkaTemplate;

  @Autowired
  private SubmissionSchemaMapper submissionSchemaMapper;

  public Submission save(Submission submission) {
    Submission savedSubmission = submissionRepository.save(submission);
    SubmissionSchema submissionSchema = submissionSchemaMapper.mapTo(savedSubmission);
    problemSubmissionKafkaTemplate.send("problem-submission", submissionSchema);
    return savedSubmission;
  }

  public Optional<Submission> getSubmissionById(String id) {
    return submissionRepository.findById(id);
  }

}
