package com.yash.yotaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestEmployeeResult {
	private String testName;
	private Integer marks;
	private Double  marksinPercentage;
	private String empName;

}
