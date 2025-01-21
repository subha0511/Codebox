package com.codebox.submission_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionDTO {

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

  private String status;

  private Date createdAt;

}
