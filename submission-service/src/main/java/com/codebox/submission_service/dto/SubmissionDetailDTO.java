package com.codebox.submission_service.dto;

import com.codebox.submission_service.pojo.SubmissionTestcase;
import com.codebox.submission_service.pojo.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionDetailDTO {

  private String id;

  @NotNull
  private String code;

  @NotNull
  private Language language;

  @NotNull
  private Long problemId;

  @NotNull
  private Long userId;

  private Long contestId;

  private Date createdAt;

  private String status;

  private List<SubmissionTestcase> submissionTestcases;

}
