package com.codebox.submission_service.domain.dto;

import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionSummaryDTO {

    private String id;

    @NotNull private String code;

    @NotNull private String language;

    @NotNull private Long problemId;

    @NotNull private Long userId;

    private Long contestId;

    private String status;

    private Date createdAt;
}
