package com.codebox.worker.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionTestcase {

  private String input;

  private String output;

  private String expectedOutput;

  private String type = "PUBLIC";

  private String status = "PENDING";

}
