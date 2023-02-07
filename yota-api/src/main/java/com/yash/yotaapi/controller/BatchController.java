package com.yash.yotaapi.controller;


import java.util.List;

import java.util.NoSuchElementException;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.yotaapi.domain.Batch;
import com.yash.yotaapi.exception.BatchNotFoundException;
import com.yash.yotaapi.exception.ErrorResponse;
import com.yash.yotaapi.exception.NoSuchElementFoundException;
import com.yash.yotaapi.service.BatchService;
import com.yash.yotaapi.util.ValidationErrorMessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
/* Batch controller is provide way to communication with client/user 
 * @author anil.shimpi
 * */

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
	@ApiOperation(tags="POST Batch", value = "CRATE Batch")
	@PostMapping("/create")
	public ResponseEntity<?> createBatch(@Valid @RequestBody Batch batch, BindingResult result ) {
		
		log.info("Batch created successfully");
		ResponseEntity<?> errorMessage=validationErrorMessageService.validationErrorMessage(result);
		if(errorMessage!=null)
			return errorMessage;
		
		batchService.createBatch(batch);
		return new ResponseEntity<Batch>(batch,HttpStatus.CREATED);
	}
		
	/* batchDetails() method retrive all batch details present in database */
	
		@ApiOperation(tags="GET all Batch",value ="Display Batch details")
		@GetMapping("/details")
		public ResponseEntity<List<Batch>> batchDetails(){
			
			log.info("Get all Batch details");
			
			List<Batch> details= batchService.getAllDetails();
			
		
			if(!details.isEmpty()) {
				
				return new ResponseEntity<List<Batch>>(details,HttpStatus.OK);
			}

			throw new BatchNotFoundException("No Batch details are present");
			
		}

		/* singleBatchDetail() method retrive particular id mention batch details */ 
		@ApiOperation(tags="GET single Batch", value = "display batch details for perticular Id")
		@GetMapping("/detail/{bid}")
		public ResponseEntity<Batch> singleBatchDetail(@PathVariable long bid){
			
			log.info("display batch details for perticular Id");
			try {
				
				Batch detail=batchService.getSingleBatchDetail(bid);
				return new ResponseEntity<Batch>(detail,HttpStatus.OK);
			}
			catch(NoSuchElementException e){
				throw new NoSuchElementFoundException("Batch with this id is not found");
			}
			
		}
		
		/*
		 * @ApiOperation(tags="UPDATE batch detalis",
		 * value="Update batch details for perticular batch id")
		 * 
		 * @PutMapping("/update/{bid}") public ResponseEntity<Batch>
		 * updateDetails(@RequestBody Batch batch,@PathVariable long bid){
		 * 
		 * log.info("Update batch details"); try { Batch
		 * updateBatch=batchService.updateBatchDetails(bid); return new
		 * ResponseEntity<Batch>(updateBatch,HttpStatus.OK); } catch(Exception e){ throw
		 * new NoSuchElementFoundException("Batch with this id is not present"); }
		 * 
		 * }
		 */
		
}


