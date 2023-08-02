package com.yash.yotaapi.controller;

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
import com.yash.yotaapi.domain.Test;
import com.yash.yotaapi.service.TestService;
import com.yash.yotaapi.util.FieldErrorValidationUtillity;
import io.swagger.v3.oas.annotations.tags.Tag;



/**
 * A controller basically controls the flow of the data.
 * It controls the data flow into model object and updates the view whenever data changes.
 * @author jaie.arkadi
 *
 */

@CrossOrigin("*")
@Tag(name="Test Controller", description="Controller for Test")
@RequestMapping("/yota/api/tests")
@RestController
public class TestController {

	@Autowired
	private TestService testService;
	
	@Autowired
	private FieldErrorValidationUtillity mapValidationErrorService;
	
	/**
	 * This method will create new Test and save the Test in DB.
	 * @param Test
	 * @param result
	 * @return saved Test
	 */
	

	@PostMapping("/")
	public ResponseEntity<?> createNewTest(@Valid @RequestBody Test test, BindingResult result){
		ResponseEntity<?> errmap = mapValidationErrorService.validationError(result);
		if(errmap!=null) { 
			return errmap;
		}

		Test savedTest = testService.saveOrUpdate(test);
		return new ResponseEntity<Test>(savedTest, HttpStatus.CREATED);
	}
	/**

	 * This method is used to get Test by using Test Id.

	 * @param TestId
	 * @return Tests
	 */
	@GetMapping("/{testId}")
	public ResponseEntity<?> getTestById(@PathVariable Long testId){
		Test Test = testService.findTestById(testId);
		return new ResponseEntity<Test>(Test, HttpStatus.OK);
	}
	
	/**
	 * This method is used to get all Tests from DB.
	 * @return all Tests
	 */
	@GetMapping("/all")
	public Iterable<Test> getAllTests(){
		return testService.findAllTest();
	}
	
	/**

	 * This mentod is used to delete Test by using Test Id.

	 * @param TestId
	 * @return return message Test is deleted
	 */
	@DeleteMapping("/{testId}")
	public ResponseEntity<?> deleteTestById(@PathVariable Long testId){
		testService.deleteTestById(testId);
		return new ResponseEntity<String>("Test is deleted successfully!", HttpStatus.OK);
	}
	
	/**
	 * This mentod is used to update Test by using Test Id.
	 * @param TestId
	 * @return updated Test
	 */

	@PutMapping("/")
	public ResponseEntity<?> updateTest(@Valid @RequestBody Test test,BindingResult result)
	{
		ResponseEntity<?> errorMap= mapValidationErrorService.validationError(result);
		if (errorMap!=null) {
			return errorMap;
		}
		return new ResponseEntity<Test>(testService.updateTest(test),HttpStatus.OK);
	}
}
