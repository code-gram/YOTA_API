package com.yash.yotaapi.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TprReportDto {
	private Integer tid;
	private String trainingName;
	private Integer employeeId;
	private Double avgPercentageMarks;
	private String empName;
	private String emailId;
	//private List<TestEmployeeResult> empWiseTestResult;

}
