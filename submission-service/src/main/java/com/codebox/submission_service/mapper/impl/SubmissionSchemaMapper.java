package com.codebox.submission_service.mapper.impl;

import com.codebox.shared_dtos.schema.SubmissionSchema;
import com.codebox.submission_service.mapper.Mapper;
import com.codebox.submission_service.domain.model.Submission;
import com.codebox.submission_service.domain.pojo.SubmissionTestcase;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SubmissionSchemaMapper implements Mapper<Submission, SubmissionSchema> {

    @Override
    public SubmissionSchema mapTo(Submission submission) {
        // Convert Submission POJO to SubmissionSchema
        SubmissionSchema submissionSchema = new SubmissionSchema();

        submissionSchema.setId(submission.getId());
        submissionSchema.setCode(submission.getCode());
        submissionSchema.setLanguage(submission.getLanguage());
        submissionSchema.setProblemId(submission.getProblemId());
        submissionSchema.setUserId(submission.getUserId());
        submissionSchema.setContestId(submission.getContestId());
        submissionSchema.setStatus(submission.getStatus());

        // Map nested SubmissionTestcases
        List<com.codebox.shared_dtos.schema.SubmissionTestcase> testcaseRecords = new ArrayList<>();
        for (SubmissionTestcase testcase : submission.getSubmissionTestcases()) {
            com.codebox.shared_dtos.schema.SubmissionTestcase testcaseRecord =
                    new com.codebox.shared_dtos.schema.SubmissionTestcase();
            testcaseRecord.setInput(testcase.getInput());
            testcaseRecord.setOutput(testcase.getOutput());
            testcaseRecord.setExpectedOutput(testcase.getExpectedOutput());
            testcaseRecord.setType(testcase.getType());
            testcaseRecord.setStatus(testcase.getStatus());
            testcaseRecords.add(testcaseRecord);
        }

        submissionSchema.setSubmissionTestcases(testcaseRecords);

        return submissionSchema;
    }

    @Override
    public Submission mapFrom(SubmissionSchema submissionSchema) {
        // Convert SubmissionSchema to Submission POJO
        Submission submission = new Submission();

        submission.setId(submissionSchema.getId().toString());
        submission.setCode(submissionSchema.getCode().toString());
        submission.setLanguage(submissionSchema.getLanguage().toString());
        submission.setProblemId(submissionSchema.getProblemId());
        submission.setUserId(submissionSchema.getUserId());
        submission.setContestId(submissionSchema.getContestId());
        submission.setStatus(submissionSchema.getStatus().toString());

        // Map nested SubmissionTestcases
        List<SubmissionTestcase> testcases = new ArrayList<>();
        for (com.codebox.shared_dtos.schema.SubmissionTestcase testcaseRecord :
                submissionSchema.getSubmissionTestcases()) {
            SubmissionTestcase testcase = new SubmissionTestcase();
            testcase.setInput(testcaseRecord.getInput().toString());
            testcase.setOutput(testcaseRecord.getOutput().toString());
            testcase.setExpectedOutput(testcaseRecord.getExpectedOutput().toString());
            testcase.setType(testcaseRecord.getType().toString());
            testcase.setStatus(testcaseRecord.getStatus().toString());
            testcases.add(testcase);
        }

        submission.setSubmissionTestcases(testcases);

        return submission;
    }
}
