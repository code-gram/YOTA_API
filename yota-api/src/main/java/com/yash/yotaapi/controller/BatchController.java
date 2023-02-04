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

import com.yash.yotaapi.service.BatchService;
import com.yash.yotaapi.util.ValidationErrorMessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
/* Batch controller is provide way to communication with client/user */

@Api(tags="BatchController", value = "Controller for batch")
@RequestMapping("/api/batch")
public class BatchController {
	
	/*
	 * BatchService is a business logic layer used to interact  Controller with
	 * Service layer
	 */
	@Autowired
	private BatchService batchService;
	
	/* ValidationErroeMessageService is used to validate field error. */
	@Autowired
	ValidationErrorMessageService validationErrorMessageService;
	
	
	/* createBatch method is used to create Batch. */
	@ApiOperation(tags="POST Technology", value = "CRATE Batch")
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
