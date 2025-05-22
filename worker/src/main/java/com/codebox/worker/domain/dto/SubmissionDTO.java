package com.codebox.worker.domain.dto;

import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionDTO {

    private String id;

    @NotNull private String code;

    @NotNull private String language;

    @NotNull private Long problemId;

    @NotNull private Long userId;

    private Long contestId;

    private Date createdAt;

    private String status;

    private List<SubmissionTestcaseDTO> submissionTestcases;
}
