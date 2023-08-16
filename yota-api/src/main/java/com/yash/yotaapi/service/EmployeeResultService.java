package com.yash.yotaapi.service;

import java.util.List;

import javax.validation.Valid;

import com.yash.yotaapi.domain.EmployeeResult;

public interface EmployeeResultService {
	
	List<EmployeeResult> getMarksByOrder(String marks);


	List<EmployeeResult> save(EmployeeResult employeeResult);

}