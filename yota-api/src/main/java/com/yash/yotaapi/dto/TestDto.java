package com.yash.yotaapi.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestDto {
	
    private Long id;

    private String testTitle;

    private String description;

    private String instruction;
    
    private Date endDate; // Represents the end date
    
    private String endTime;

    private String testName;

    private int totalQuestions;

    private int totalMarks;

    private int totalTime;

    private String testType;


}
