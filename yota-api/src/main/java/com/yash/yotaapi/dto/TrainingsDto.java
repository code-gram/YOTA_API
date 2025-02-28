package com.yash.yotaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainingsDto {

    private Long id;

    private String trainingName;

    private String assignTo;

    private String startDate;

    private String endDate;

    private int totalNominations;

    private String status;
}
