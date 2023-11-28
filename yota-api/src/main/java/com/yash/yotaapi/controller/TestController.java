package com.yash.yotaapi.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * @author dimpal.gaur
 *
 */

@CrossOrigin("*")
@Tag(name = "Test Controller", description = "Controller for Test")
@RequestMapping("/yota/api/test")
@RestController
public class TestController 
{
	@Autowired
    private TestService testService; 
	
	/**
	 * getTestList method will control the request to get all test from DB
	 * @return
	 */
	@GetMapping("/getTestList")
	public Iterable<Test> getTestList() {
		Iterable<Test> list = testService.findAllTest();
		return list;
	}

	/**
	 * createNewTest method will control the request to create new test and save it in DB.
	 * @param test
	 * @return test object
	 */
	@PostMapping("/add")
	public ResponseEntity<?> createNewTest(@Valid @RequestBody Test test) 
	{
		Test savedTest = testService.saveOrUpdate(test);
		return new ResponseEntity<Test>(savedTest, HttpStatus.CREATED);
	}

	/**
	 * getTestById method will control the request for find test based on id from DB
	 * @param id
	 * @return test object
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getTestById(@PathVariable long id) {
		Optional<Test> test = testService.findTestById(id);
		if (test.isPresent()) {
			return new ResponseEntity<>(test.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Test Not Found", HttpStatus.OK);
		}
	}

	/**
	 * deleteTestsById method will control the delete request based on id from DB
	 * @param id
	 * @return String
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTestsById(@PathVariable long id) {
		testService.deleteTestById(id);
		return new ResponseEntity<String>("test is deleted successfully!", HttpStatus.OK);
	}

	/**
	 * updateTest method will control the update request for test
	 * @param test
	 * @return test object
	 */
	@PutMapping("/")
	public ResponseEntity<?> updateTest(@Valid @RequestBody Test test) {
		return new ResponseEntity<Test>(testService.updateTest(test), HttpStatus.OK);
	}
}
