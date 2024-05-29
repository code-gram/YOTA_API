package com.yash.yotaapi.dto;

import com.yash.yotaapi.entity.Result;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


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
    private String startDate;
    private Date endDate;
    private Date created_at;
    private Date modified_at;
    private String endTime;
//    private List<YotaUser> assign;
    private Result result;
    private String testType;
}
