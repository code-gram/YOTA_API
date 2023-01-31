package com.yash.yotaapi.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.yotaapi.domain.Batch;
import com.yash.yotaapi.exception.ElementNotFoundException;
import com.yash.yotaapi.service.BatchService;

import com.yash.yotaapi.serviceimpl.ValidationErrorMessageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
//batch controller
@RequestMapping("/api/batch")
public class BatchController {
	
	//Autowired services
	@Autowired
	BatchService batchService;
	@Autowired
	ValidationErrorMessageService validationErrorMessageService;
	
	//create batch
	@PostMapping("/create")
	public ResponseEntity<?> createBatch(@Valid @RequestBody Batch batch, BindingResult result ) {
		
		log.info("Batch created successfully");
		ResponseEntity<?> errorMessage=validationErrorMessageService.validationErrorMessage(result);
		if(errorMessage!=null)
			return errorMessage;
		
		batchService.createBatch(batch);
		return new ResponseEntity<Batch>(batch,HttpStatus.CREATED);
	}
}
