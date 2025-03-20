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

    // Assuming startTime should be a String representing time
    private String startTime; // Consider changing to LocalTime if needed

    // Corrected the pattern for endDate and ensured proper date format
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate; // Ensure frontend sends a valid date

    private Integer durationTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt; // Ensure frontend sends a valid date

    private int totalQuestions;
    private int totalMarks;
    private int totalTime;
    private int totalAssociateCount;
    private String status;
    private String testStatus;

}