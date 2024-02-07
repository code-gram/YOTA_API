package com.yash.yotaapi.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.yash.yotaapi.domain.*;
import com.yash.yotaapi.service.*;
import com.yash.yotaapi.util.*;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * Controller for training.
 * 
 * Manages CRUD operations for Training entities.
 *
 * 
 * 
 * @author raghav.muchhal
 * 
 */

@CrossOrigin("*")

@RestController

@Tag(name = "Training Controller", description = "Controller for training")

@RequestMapping("/trainings")

public class TrainingController {
	@Autowired
	private TrainingService trainingService;
	@Autowired
	private FieldErrorValidationUtillity fieldErrorValidationUtility;
	@Autowired
	private CompareDateValidator compareDateValidator;

	/**
	 * Create a new training.
	 *
	 * @param training  The training object to be created.
	 * @param result    Binding result for validation.
	 * 
	 * @param principal Authenticated user's details.
	 * 
	 * @return The created training object.
	 * 
	 */

	@PostMapping("/")

	public ResponseEntity<Training> createTraining(@Validated @RequestBody Training training, BindingResult result,
		Principal principal) {
		String username = principal.getName();
		System.out.println(training.getTrainingName());
		ResponseEntity<Map<String, String>> errorMessage = fieldErrorValidationUtility.validationError(result);

		trainingService.createTraining(training);

		return new ResponseEntity<Training>(training, HttpStatus.CREATED);

	}

	/**
	 * Get all trainings.
	 * @return List of all training details.
	 * 
	 */

	@GetMapping("/")
	public ResponseEntity<List<Training>> getAllTrainings() {
		return new ResponseEntity<List<Training>>(trainingService.getAllDetails(), HttpStatus.OK);
	}

	/**
	 * Get training details by ID.
	 * @param trainingId The ID of the training to retrieve.
	 * @return The training details with the given ID.
	 * 
	 */
	@GetMapping("/{trainingId}")
	public ResponseEntity<Training> findDetailByTrainingIdentifier(@PathVariable long trainingId) {
		Training details = trainingService.getTraining(trainingId);
		return new ResponseEntity<Training>(details, HttpStatus.OK);
	}

	/**
	 * Update training details.
	 * @param training   The updated training details.
	 * @param trainingId The ID of the training to update.
	 * @param principal  Authenticated user's details.
	 * @return The updated training object.
	 * 
	 */

	@PutMapping("/{trainingId}")

	public ResponseEntity<?> updateDetails(@Validated @RequestBody Training training,
			@PathVariable long trainingId, BindingResult result, Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here
		ResponseEntity<?> errorMap = fieldErrorValidationUtility.validationError(result);
		if (errorMap != null) {
			return errorMap;
		}
		return new ResponseEntity<Training>(trainingService.updateTrainingDetails(training, trainingId), HttpStatus.OK);
	}

	/**
	 * 
	 * Remove a training by ID.
	 * @param trainingId The ID of the training to delete.
	 * @return Success message upon deletion.
	 * 
	 */

	@DeleteMapping("/{trainingId}")

	public ResponseEntity<?> removeTraining(@PathVariable long trainingId) {

		trainingService.removeTrainingDetails(trainingId);

		return new ResponseEntity<String>("Training with id: " + trainingId + " is deleted successfully",
				HttpStatus.OK);

	}

	/**
	 * 
	 * Search trainings by a keyword.
	 *
	 * 
	 * 
	 * @param keyword The keyword to search for in training details.
	 * 
	 * @return List of trainings matching the keyword.
	 * 
	 */

	@GetMapping("/search/{keyword}")

	public ResponseEntity<List<Training>> searchTraining(@PathVariable("keyword") String keyword) {

		List<Training> searchTraining = trainingService.searchTraining(keyword);

		return new ResponseEntity<List<Training>>(searchTraining, HttpStatus.OK);

	}

	/**
	 * 
	 * Get trainings within a specified start and end date range.
	 *
	 * 
	 * 
	 * @param startDate The start date of the range.
	 * 
	 * @param endDate   The end date of the range.
	 * 
	 * @return List of trainings within the specified date range.
	 * 
	 */

	@GetMapping("/search/{startDate}/{endDate}")

	public ResponseEntity<List<Training>> getByStartDateAndEndDate(

			@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,

			@PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

		List<Training> search = trainingService.getByStartDateAndEndDate(startDate, endDate);

		return new ResponseEntity<List<Training>>(search, HttpStatus.OK);

	}

}