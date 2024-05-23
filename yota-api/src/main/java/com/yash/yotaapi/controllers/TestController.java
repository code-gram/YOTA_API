package com.yash.yotaapi.controllers;

import com.yash.yotaapi.entity.Test;
import com.yash.yotaapi.services.IServices.ITestService;
import com.yash.yotaapi.validators.IsTechnicalManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private ITestService testService;
	

 	@PostMapping("/add-test")
    @IsTechnicalManager
    public ResponseEntity<String> addTest(@RequestBody Test test) {
        return new ResponseEntity<>(this.testService.addTest(test),
				                     HttpStatus.OK);
    }
		
// 	 @GetMapping("/")
//	    public ResponseEntity<List<TestDto>> fetchAllTest() {
//	        return new ResponseEntity<List<TestDto>>(testService.fetchAllTest(), HttpStatus.OK);
//	    }
//
//	@GetMapping("/testPaper")
//	@IsAssociate
//	public ResponseEntity<Optional<TestDto>> testPaper(@RequestParam("id") Long id) {
//		Optional<TestDto> test = this.testService.findById(id);
//		return new ResponseEntity<>(test, HttpStatus.OK);
//	}
	   
}
