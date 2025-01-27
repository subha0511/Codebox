package com.codebox.shared_dtos.domain.problem;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemDetailDTO {

    private long id;

    private String title;

    private String description;

    private List<ProblemTestcaseDTO> testcases;
}
