package com.yash.yotaapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.yotaapi.domain.Test;
import com.yash.yotaapi.exception.ErrorResponse;
import com.yash.yotaapi.service.TestService;
import com.yash.yotaapi.serviceimpl.ValidationErrorMessageService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/api/test")
public class TestController {
	
	@Autowired
	TestService testService;
	@Autowired
	ValidationErrorMessageService validationErrorMessageService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createTest(@Valid @RequestBody Test test,BindingResult result){
		
		log.info("Test created successfully");
		ResponseEntity<?> errorMessage=validationErrorMessageService.ValidationErrorMessage(result);
		if(errorMessage!=null)
		return errorMessage;
		
		testService.createTest(test);
		return new ResponseEntity<Test>(test,HttpStatus.CREATED);
		
		
		
	}
	

}
