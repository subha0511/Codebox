package com.codebox.worker.dto;

import com.codebox.worker.pojo.SubmissionTestcase;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submission {

  private String id;

  @NotNull
  private String code;

  @NotNull
  private String language;

  @NotNull
  private Long problemId;

  @NotNull
  private Long userId;

  private Long contestId;

  private Date createdAt;

  private String status;

  private List<SubmissionTestcase> submissionTestcases;

}
