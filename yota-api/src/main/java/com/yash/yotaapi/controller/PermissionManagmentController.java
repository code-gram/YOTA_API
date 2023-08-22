package com.yash.yotaapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.yotaapi.domain.EmployeeResult;
import com.yash.yotaapi.domain.ParentTechnology;
import com.yash.yotaapi.service.EmployeeResultService;

@CrossOrigin("*")
@RestController

@RequestMapping("/yota/api/marks")
public class PermissionManagmentController {
	
	@Autowired
	private EmployeeResultService employeeResultService;
	
	/**
	 * getTech method is used to get ParentTechnology from DB on basis name.
	 * @param name
	 * @return value based 
	 */

	@GetMapping("/{marksOrder}")
	public ResponseEntity<List<EmployeeResult>> getEmployeeResult(@PathVariable(value ="marksOrder") String marksOrder)
	{
		return new ResponseEntity<List<EmployeeResult>>(employeeResultService.getMarksByOrder(marksOrder),HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<List<EmployeeResult>> postEmployeeResult(@RequestBody EmployeeResult employeeResult)
	{
		return new ResponseEntity<List<EmployeeResult>>(employeeResultService.save(employeeResult),HttpStatus.OK);
	}
}
