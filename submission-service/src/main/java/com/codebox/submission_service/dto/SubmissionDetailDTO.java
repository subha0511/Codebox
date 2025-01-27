package com.codebox.submission_service.dto;

import com.codebox.submission_service.pojo.SubmissionTestcase;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionDetailDTO {

    private String id;

    @NotNull private String code;

    @NotNull private String language;

    @NotNull private Long problemId;

    @NotNull private Long userId;

    private Long contestId;

    private Date createdAt;

    private String status;

    private List<SubmissionTestcase> submissionTestcases;
}
