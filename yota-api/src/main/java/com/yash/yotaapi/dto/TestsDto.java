package com.yash.yotaapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date endDate;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created_at;
    private Date modified_at;
    private String endTime;
//    private List<YotaUser> assign;
    private Result result;
    private String testType;
}
