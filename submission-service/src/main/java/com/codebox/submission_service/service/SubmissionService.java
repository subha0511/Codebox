package com.codebox.submission_service.service;

import com.codebox.submission_service.domain.model.Submission;
import com.codebox.submission_service.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubmissionService {

  @Autowired
  MongoTemplate mongoTemplate;

  @Autowired
  SubmissionRepository submissionRepository;

  @Autowired
  KafkaTemplate<String, Object> problemSubmissionKafkaTemplate;
//
//  @Autowired
//  private SubmissionSchemaMapper submissionSchemaMapper;

  public Submission save(Submission submission) {
    Submission savedSubmission = submissionRepository.save(submission);
//        SubmissionSchema submissionSchema = submissionSchemaMapper.mapTo(savedSubmission);
    problemSubmissionKafkaTemplate.send("problem-submission", savedSubmission);
    return savedSubmission;
  }

  public Optional<Submission> getSubmissionById(String id) {
    return submissionRepository.findById(id);
  }

  public void updateSubmission(Submission submission) {
    Query query = new Query(Criteria.where("id")
        .is(submission.getId()));
    Submission existingSubmission = mongoTemplate.findOne(query, Submission.class);
    Update update = new Update().set("status", submission.getStatus())
        .set("submissionTestcases", submission.getSubmissionTestcases());
    mongoTemplate.updateFirst(query, update, Submission.class);
  }
}
