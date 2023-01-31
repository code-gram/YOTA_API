package com.yash.yotaapi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.yotaapi.domain.Test;
import com.yash.yotaapi.exception.ErrorResponse;

@RestController
@RequestMapping("api/test")
public class TestController {
	
	
	public ResponseEntity<ErrorResponse> createTest(@Valid @RequestBody Test test){
		
		
		return null;
		
	}
	

}
