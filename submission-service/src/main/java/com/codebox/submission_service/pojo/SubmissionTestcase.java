package com.codebox.submission_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionTestcase {

  private String input;

  private String output;

  private String expectedOutput;

  private String type = "PUBLIC";

  private String status = "PENDING";

}
