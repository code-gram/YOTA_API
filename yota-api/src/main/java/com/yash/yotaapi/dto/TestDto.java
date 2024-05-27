package com.yash.yotaapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
    private Date endDate; // Represents the end date
    private String startTime;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date createdAt;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date modifiedAt;

//    private String testName;
    private int totalQuestions;
    private int totalMarks;
    private int totalTime;

    @PrePersist
    public void created_At() {
        this.createdAt = new Date();
    }

    @PreUpdate
    public void modified_At() {
        this.modifiedAt = new Date();
    }
}
