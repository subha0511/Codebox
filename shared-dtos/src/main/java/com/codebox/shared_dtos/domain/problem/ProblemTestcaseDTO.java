package com.codebox.shared_dtos.domain.problem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProblemTestcaseDTO {

    private String input;

    private String output;

    private String type;
}
