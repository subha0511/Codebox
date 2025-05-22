package com.codebox.problem_service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemSummaryDTO {

    private long id;

    private String title;

    private String description;
}
