package com.codebox.submission_service.model;

import com.codebox.submission_service.pojo.SubmissionTestcase;
import com.codebox.submission_service.pojo.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "submissions")
public class Submission {

  @Id
  private String id;

  @NotNull
  private String code;

  @NotNull
  private Language language;

  @NotNull
  @Field(name = "problem_id")
  private Long problemId;

  @NotNull
  @Field(name = "user_id")
  private Long userId;

  @Field(name = "contest_id")
  private Long contestId;

  @CreatedDate
  @Field(name = "created_at")
  private Date createdAt;

  private String status = "PENDING";

  private List<SubmissionTestcase> submissionTestcases;

}
