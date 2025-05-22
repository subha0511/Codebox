package com.codebox.worker.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionTestcaseDTO {

    private String input;

    private String output;

    private String expectedOutput;

    private String type = "PUBLIC";

    private String status = "PENDING";
}
