package com.yash.yotaapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.yotaapi.dto.TechnologyDto;
import com.yash.yotaapi.dto.TestDto;
import com.yash.yotaapi.entity.Technology;
import com.yash.yotaapi.services.IServices.ITechnologyService;
import com.yash.yotaapi.services.IServices.ITestService;
import com.yash.yotaapi.validators.IsTechnicalManager;

@RestController
@RequestMapping("/tests")
public class TestController {
	
	@Autowired
	private ITestService testService;
	

 	@PostMapping("/addTest")
    @IsTechnicalManager
    public ResponseEntity<TestDto> addTest(@RequestBody TestDto testDto
    		) {
        return new ResponseEntity<>(this.testService.addTest(testDto), HttpStatus.OK);
    }
		
 	 @GetMapping("/")
	    public ResponseEntity<List<TestDto>> fetchAllTest() {
	        return new ResponseEntity<List<TestDto>>(testService.fetchAllTest(), HttpStatus.OK);
	    }	
	   
}
