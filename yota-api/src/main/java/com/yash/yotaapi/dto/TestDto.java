package com.yash.yotaapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestDto {
	
    private Long id;
    private String testTitle;
    private String type;
    private String description;
    private String instruction;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private String startTime;
    private Date endDate; // Represents the end date
    private Integer durationTime;
    private Date createdAt;
    private int totalQuestions;
    private int totalMarks;
    private int totalTime;
    private int totalAssociateCount;
}
