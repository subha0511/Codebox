package com.codebox.contest_service.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContestDetailsDTO {

    private long id;

    private String title;

    private List<Long> problems;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
