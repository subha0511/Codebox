package com.codebox.problem_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestcaseDTO {

    private String input;

    private String output;

    private String type;
}
