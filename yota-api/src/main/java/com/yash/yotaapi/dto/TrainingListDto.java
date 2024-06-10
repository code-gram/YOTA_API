package com.yash.yotaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainingListDto {
    private Long id;

    private String trainingName;

    private String assignTo;

    private Date startDate;

    private Date endDate;

    private String status;

    private int totalNominations;

    private int registeredInTraining;
}
