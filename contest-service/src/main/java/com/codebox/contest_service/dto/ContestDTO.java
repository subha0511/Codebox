package com.codebox.contest_service.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContestDTO {

    private long id;

    private String title;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
