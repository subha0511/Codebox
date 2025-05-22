package com.codebox.submission_service.domain.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemDTO {

    private long id;

    private String title;

    private String description;

    private List<ProblemTestcaseDTO> testcases;
}
