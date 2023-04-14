package com.yash.yotaapi.controller;

import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.yotaapi.domain.Batch;
import com.yash.yotaapi.service.BatchService;
import com.yash.yotaapi.util.CompareDateValidator;
import com.yash.yotaapi.util.FieldErrorValidationUtillity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 * Batch controller is provide way to communication with client/user
 * 
 * @author anil.shimpi
 */
@RestController
@Api(tags = "BatchController", value = "Controller for batch")
@RequestMapping("/yota/api/batches")
@Validated
 public class BatchController {

	/*
	 * BatchService is a business logic layer used to interact Controller with
	 * Service layer
	 */
	@Autowired
	private BatchService batchService;

	/* ValidationErroeMessageService is used to validate field error. */
	@Autowired
	FieldErrorValidationUtillity fileErrorValidationUtillity;

	@Autowired
	CompareDateValidator compareDateValidator;

	/* createBatch method is used to create Batch. */
	@ApiOperation(tags = "POST Batch", value = "CRATE Batch")
	@PostMapping("/")
	public ResponseEntity<?> createBatch(@Valid @RequestBody Batch batch, BindingResult result) {

		ResponseEntity<?> errorMessage = fileErrorValidationUtillity.validationError(result);

		if (errorMessage != null)
			return errorMessage;

		batchService.createBatch(batch);
		return new ResponseEntity<Batch>(batch, HttpStatus.CREATED);
	}

	/* batchDetails() method retrive all batch details present in database */

	@ApiOperation(tags = "GET all Batch", value = "Display Batch details")
	@GetMapping("/")
	public ResponseEntity<List<Batch>> getAllBatch() {

		return new ResponseEntity<List<Batch>>(batchService.getAllDetails(), HttpStatus.OK);

	}

	/* singleBatchDetail() method retrive particular id mention batch details */
	@ApiOperation(tags = "GET single Batch", value = "display batch details for perticular Id")
	@GetMapping("/{bIdentifier}")
	public ResponseEntity<Batch> findDetailByBatchIdentifier(@PathVariable String bIdentifier) {

		Batch details = batchService.getBatch(bIdentifier);
		return new ResponseEntity<Batch>(details, HttpStatus.OK);

	}

	/*
	 * updateDetails api update batch details according to batchId entered by User.
	 */
	@ApiOperation(tags = "UPDATE batch", value = "Update batch details for perticular batch id")
	@PutMapping("/")
	public ResponseEntity<?> updateDetails(@Valid @RequestBody Batch batch, BindingResult result) {

		ResponseEntity<?> errorMap = fileErrorValidationUtillity.validationError(result);

		if (errorMap != null) {
			return errorMap;
		}

		return new ResponseEntity<Batch>(batchService.updateBatchDetails(batch), HttpStatus.OK);

	}

	/* deleteBatch api delete batch accourding to batchId enter by user. */
	@ApiOperation(tags = "DELETE batch", value = "Delete batch details for perticular batchId")
	@DeleteMapping("/{batchId}")
	public ResponseEntity<?> removeBatch(@PathVariable long batchId) {

		batchService.removeBatchDetails(batchId);

		return new ResponseEntity<String>("Batch with id: " + batchId + " is delete sucessfully", HttpStatus.OK);
	}

	/*
	 * searchBatch api is display batch details accourding to keyword enter by user.
	 */
	@ApiOperation(tags = "SEARCH batch", value = "Get batch by keyword")
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<Batch>> searchBatch(@PathVariable("keyword") String keyword) {

		List<Batch> searchBatch = batchService.searchBatch(keyword);
		return new ResponseEntity<List<Batch>>(searchBatch, HttpStatus.OK);

	}

	// this Api display batch details according to start date and end date by uset. 
	@GetMapping("/search/{startDate}/{endDate}")
	public ResponseEntity<List<Batch>> getByStartDateAndEndDate(
			@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
		List<Batch> search = batchService.getByStartDateAndEndDate(startDate, endDate);
		return new ResponseEntity<List<Batch>>(search, HttpStatus.OK);
	}

}
