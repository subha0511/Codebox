package com.codebox.worker.service;

import com.codebox.shared_dtos.schema.SubmissionSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaPollerService {

  @KafkaListener(topics = "problem-submission", groupId = "submission-worker")
  public void consume(SubmissionSchema submissionSchema) {
    System.out.println(
        "Consumed message: " + submissionSchema.getId() + " " + submissionSchema.getProblemId()
            + " " + submissionSchema.getLanguage());
  }

}
