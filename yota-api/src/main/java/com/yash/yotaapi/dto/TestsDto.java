package com.yash.yotaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestsDto {

    private Long id;

    private String testName;

    private LocalDateTime startDate; // Represents the end date

    private LocalDateTime endDate;

    private String action;
}
