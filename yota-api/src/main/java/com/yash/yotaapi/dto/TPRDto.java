package com.yash.yotaapi.dto;

import com.yash.yotaapi.constants.UserAccountStatusTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TPRDto {
    private Integer tid;
    private String trainingName;
    private Long empId;
    private Double avgPercentageMarks;
    private String fullName;
    private String testIds;
    private String feedback;
    private UserAccountStatusTypes accountStatus;
    private String emailId;
}
