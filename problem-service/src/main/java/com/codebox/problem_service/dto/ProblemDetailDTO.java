package com.codebox.problem_service.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProblemDetailDTO {

    private long id;

    private String title;

    private String description;

    private List<TestcaseDTO> testcases;
}
