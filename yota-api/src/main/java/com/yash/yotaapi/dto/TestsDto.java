package com.yash.yotaapi.dto;

import com.yash.yotaapi.entity.Result;
import com.yash.yotaapi.entity.YotaUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestsDto {

    private Long id;
    private String testTitle;
    private String testDescription;
    private String testInstruction;
    private String action;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;
    private String endTime;
    private List<YotaUser> assign;
    private Result result;
    private String testType;
}
