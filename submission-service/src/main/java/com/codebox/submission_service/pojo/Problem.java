package com.codebox.submission_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Problem {

  private long id;

  private String title;

  private String description;

  private List<Testcase> testcases;

}
