package com.yash.yotaapi.controller;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.yotaapi.domain.AssociateDetailsTest;
import com.yash.yotaapi.domain.Test;
import com.yash.yotaapi.exception.TestAlreadyExistsException;
import com.yash.yotaapi.service.TestService;
import com.yash.yotaapi.util.DateValidationUtility;
import com.yash.yotaapi.util.FieldErrorValidationUtillity;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller for test. Manages CRUD operations for test entities.
 *
 * @author gaurav.patil
 */
@CrossOrigin("*")
@RestController
@Tag(name = "Test Controller", description = "Controller for test")
@RequestMapping("/tests")
public class TestController {

	@Autowired
	TestService testService;

	/* ValidationErroeMessageService is used to validate field error. */
	@Autowired
	FieldErrorValidationUtillity fileErrorValidationUtillity;

	@Autowired
	DateValidationUtility dateValidationUtility;

	/**
	 * Endpoint to add a new test to the system. This method handles HTTP POST
	 * requests at the "/" endpoint. It accepts a JSON representation of the test in
	 * the request body, adds the test to the system, and returns a ResponseEntity
	 * containing the added test and an HTTP status code.
	 * 
	 * Before adding new test it checks if a test with the given name already exists
	 * in the test_masters table. If the test does not exists, it is added to the
	 * table.If the test already exists, a {@link TestAlreadyExistsException} is
	 * thrown.
	 * 
	 * @param test The test details to be added (provided in the request body).
	 * @return A ResponseEntity with the added test and an HTTP status code.
	 */
	@PostMapping("/")
	public ResponseEntity<?> addTest(@Valid @RequestBody Test test, BindingResult result) {
		ResponseEntity<?> errorMessage = fileErrorValidationUtillity.validationError(result);
		
		dateValidationUtility.validateDateRange(test.getStartDate(), test.getEndDate());

		if (errorMessage != null)
			return errorMessage;
		else {
			if (!testService.getTestByName(test.getName())) {

				testService.addTest(test);
				return new ResponseEntity<Test>(test, HttpStatus.CREATED);
			} else {
				throw new TestAlreadyExistsException("Test Already Exists...");
			}
		}
		
	}

	/**
	 * Endpoint to retrieve a list of all test in the system. This method handles
	 * HTTP GET requests at the "/" endpoint.
	 *
	 * @return A ResponseEntity with a list of all test and an HTTP status code.
	 */
	@GetMapping("/")
	public ResponseEntity<List<Test>> getAllTest() {
		return new ResponseEntity<List<Test>>(testService.getAllTest(), HttpStatus.OK);
	}

	/**
	 * Endpoint to retrieve the details of a test based on its ID.
	 *
	 * This method handles HTTP GET requests at the "/{id}" endpoint. It takes the
	 * test ID as a path variable, retrieves the details of the corresponding test,
	 * and returns a ResponseEntity containing the test details and an HTTP status
	 * code.
	 *
	 * @param id The ID of the test to be retrieved (provided as a path variable).
	 * @return A ResponseEntity with the details of the requested test and an HTTP
	 *         status code.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Test> getTestById(@PathVariable(value = "id") long id) {
		return new ResponseEntity<Test>(testService.getTestById(id), HttpStatus.OK);
	}

	/**
	 * Endpoint to delete a test from the system based on its ID.
	 *
	 * This method handles HTTP DELETE requests at the "/deleteTesttById/{id}"
	 * endpoint. It takes the test ID as a path variable, deletes the corresponding
	 * test from the system, and returns a ResponseEntity with a message and an HTTP
	 * status code.
	 *
	 * @param id The ID of the test to be deleted (provided as a path variable).
	 * @return A ResponseEntity with a deletion message and an HTTP status code.
	 */
	@DeleteMapping("/deleteTestById/{id}")
	public ResponseEntity<?> deleteTest(@PathVariable(value = "id") long id) {
		testService.deleteTestById(id);
		return new ResponseEntity<String>("Test of ID :" + id + " deleted...", HttpStatus.OK);
	}

	/**
	 * Endpoint to update the details of a test in the system based on its ID.
	 *
	 * This method handles HTTP PUT requests at the "/updateT/{id}" endpoint. It
	 * takes the test ID as a path variable and the updated test details in the
	 * request body. The method updates the test with the specified ID and returns a
	 * ResponseEntity containing the updated test details and an HTTP status code.
	 *
	 * @param id                The ID of the test to be updated (provided as a path
	 *                          variable).
	 * @param updatedCompetency The updated details for the test (provided in the
	 *                          request body).
	 * @return A ResponseEntity with the updated test details and an HTTP status
	 *         code.
	 */
	@PutMapping("/updateTest/{id}")
	public ResponseEntity<?> updateTest(@PathVariable long id, @Valid @RequestBody Test test) {
		Test updatedTest = testService.updateTest(id, test);
		return new ResponseEntity<>(updatedTest, HttpStatus.OK);

	}
	
	

	@GetMapping("{id}/assignedTests")
	public ResponseEntity<Set<Test>> getAssignedTestDetails(@PathVariable Long id, Principal principal){
		//String username = principal.getName();
		Set<Test> testSet =testService.getAssignedTests(id);
		return ResponseEntity.ok().body(testSet);
	}
	
	/**
	 * Endpoint to retrieve a list of Test assigned  for loged in  associate This method handles
	 * HTTP GET requests at the "/" endpoint.
	 *
	 * @return A set of AssociateDetest for assigned tests tests  and an HTTP status code.
	 */
	
	@GetMapping("/assignedTests")
	public Set<AssociateDetailsTest> getAssignedTestDetails(Principal principal){
		String username = principal.getName();
		Set<AssociateDetailsTest> testSet =testService.getAssignedTests(username);
		return testSet;
	}


}
