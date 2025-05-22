package com.codebox.problem_service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProblemDTO {

  private long id;

  private String title;

  private String description;

  private List<TestcaseDTO> testcases;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class TestcaseDTO {

    private String input;

    private String output;

    private String type;
  }

}