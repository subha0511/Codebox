package com.codebox.worker.service;

import com.codebox.worker.client.SubmissionClient;
import com.codebox.worker.domain.dto.SubmissionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaService {

  @Autowired
  SubmissionClient submissionClient;

  @Autowired
  TestcaseExecutorService testcaseExecutorService;

  @KafkaListener(topics = "problem-submission", groupId = "submission-worker")
  public void consume(SubmissionDTO submissionDTO) {
    try {
      SubmissionDTO output = testcaseExecutorService.processSubmission(submissionDTO);
      submissionClient.updateSubmission(output.getId(), output);
    } catch (Exception e) {
      System.out.println("Error in Kafka Service");
      e.printStackTrace();
    }
  }

}
