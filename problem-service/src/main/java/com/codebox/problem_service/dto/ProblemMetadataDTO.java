package com.codebox.problem_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemMetadataDTO {

    private long id;

    private String title;

    private String description;
}
