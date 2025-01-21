package com.codebox.contest_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
