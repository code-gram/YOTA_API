package com.yash.yotaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestResultDto {
    private Long id;

    private Long testId;

    private String userEmailId;

    private Long result;

    private String startTime;

    private String endTime;

    private String timeTaken;

}
