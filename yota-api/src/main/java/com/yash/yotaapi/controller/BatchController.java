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
import org.springframework.web.bind.annotation.CrossOrigin;
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
import io.swagger.v3.oas.annotations.tags.Tag;



/*
 * Batch controller is provide way to communication with client/user
 * 
 * @author anil.shimpi
 */
@CrossOrigin("*")
@RestController

@Tag(name="Batch Controller", description="Controller for batch")

@RequestMapping("/yota/api/batches")
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

	@PostMapping("/")
	public ResponseEntity<?> createBatch(@Validated @RequestBody Batch batch, BindingResult result) {

		ResponseEntity<?> errorMessage = fileErrorValidationUtillity.validationError(result);

		if (errorMessage != null)
			return errorMessage;

		batchService.createBatch(batch);
		return new ResponseEntity<Batch>(batch, HttpStatus.CREATED);
	}

	/* batchDetails() method retrive all batch details present in database */


	@GetMapping("/")
	public ResponseEntity<List<Batch>> getAllBatch() {

		return new ResponseEntity<List<Batch>>(batchService.getAllDetails(), HttpStatus.OK);

	}

	/* singleBatchDetail() method retrive particular id mention batch details */

	
	@GetMapping("/{id}")
	public ResponseEntity<Batch> findDetailByBatchIdentifier(@PathVariable long id) {


		Batch details = batchService.getBatch(id);
		return new ResponseEntity<Batch>(details, HttpStatus.OK);

	}

	/*
	 * updateDetails api update batch details according to batchId entered by User.
	 */

	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateDetails(@Validated @RequestBody Batch batch, @PathVariable long id,BindingResult result) {


		ResponseEntity<?> errorMap = fileErrorValidationUtillity.validationError(result);
		System.out.println("API hit");
		if (errorMap != null) {
			return errorMap;
		}

		return new ResponseEntity<Batch>(batchService.updateBatchDetails(batch,id), HttpStatus.OK);

	}

	/* deleteBatch api delete batch accourding to batchId enter by user. */

	@DeleteMapping("/{batchId}")
	public ResponseEntity<?> removeBatch(@PathVariable long batchId) {

		batchService.removeBatchDetails(batchId);

		return new ResponseEntity<String>("Batch with id: " + batchId + " is delete sucessfully", HttpStatus.OK);
	}

	/*
	 * searchBatch api is display batch details accourding to keyword enter by user.
	 */

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
