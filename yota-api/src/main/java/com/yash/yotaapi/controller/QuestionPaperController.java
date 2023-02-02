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

import com.yash.yotaapi.domain.QuestionPaper;
import com.yash.yotaapi.exception.ErrorResponse;
import com.yash.yotaapi.service.QuestionPaperService;
import com.yash.yotaapi.serviceimpl.ValidationErrorMessageService;

import lombok.extern.slf4j.Slf4j;
/**
 * QuestionPaperController interact with service layer to complete the work 
 * according to web request.
 *
 */
@Slf4j
@RestController
@RequestMapping("/api/test")
public class QuestionPaperController {
	
	@Autowired
	QuestionPaperService testService;
	@Autowired
	ValidationErrorMessageService validationErrorMessageService;
	
	/**
	 * This Controller Method Handles The HTTP Request Of QuestionPaper,  
	 * Matching With Given URI
	 */
	@PostMapping("/create")
	public ResponseEntity<?> createTest(@Valid @RequestBody QuestionPaper test,BindingResult result){
		
		log.info("Test created successfully");
		ResponseEntity<?> errorMessage=validationErrorMessageService.ValidationErrorMessage(result);
		if(errorMessage!=null)
		return errorMessage;
		
		testService.createTest(test);
		return new ResponseEntity<QuestionPaper>(test,HttpStatus.CREATED);
		
		
		
	}
	

}
