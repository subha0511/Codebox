package com.codebox.submission_service.domain.model;

import com.codebox.submission_service.domain.pojo.SubmissionTestcase;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "submissions")
public class Submission {

    @Id
    private String id;

    @NotNull private String code;

    @NotNull private String language;

    @NotNull @Field(name = "problem_id")
    private Long problemId;

    @NotNull @Field(name = "user_id")
    private Long userId;

    @Field(name = "contest_id")
    private Long contestId;

    @CreatedDate
    @Field(name = "created_at")
    private Date createdAt;

    private String status = "PENDING";

    private List<SubmissionTestcase> submissionTestcases;
}
