package com.codebox.worker.service;

import com.codebox.shared_dtos.schema.SubmissionSchema;
import com.codebox.shared_dtos.schema.SubmissionTestcase;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExecutorService {

    @Autowired
    private DockerService dockerService;

    public void processSubmission(SubmissionSchema submission) {
        String code = submission.getCode().toString();
        List<SubmissionTestcase> testCases = submission.getSubmissionTestcases();
        List<SubmissionTestcase> results = new ArrayList<>();

        try {
            System.out.println(dockerService.executeCodeInContainer(
                    code, testCases, submission.getLanguage().toString()));
        } catch (Exception e) {
            System.out.println("Error in executing code in container" + e);
        }
    }
}
