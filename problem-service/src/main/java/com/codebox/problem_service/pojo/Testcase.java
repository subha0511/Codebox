package com.codebox.problem_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Testcase {

  private String input;

  private String output;

  private String type;

}
