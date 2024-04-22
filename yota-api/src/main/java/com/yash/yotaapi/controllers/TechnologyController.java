package com.yash.yotaapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.yotaapi.entity.Technology;
import com.yash.yotaapi.services.IServices.ITechnologyService;
import com.yash.yotaapi.validators.IsTechnicalManager;

@RestController
@RequestMapping("/technology")
public class TechnologyController {
	
		@Autowired
		private ITechnologyService service;
	
	 	@PostMapping("/addTechnology/{technology}")
	    @IsTechnicalManager
	    public ResponseEntity<Technology> addTechnology(@PathVariable("technology") String technology
	    		) {
	        return new ResponseEntity<>(this.service.addTechnology(technology), HttpStatus.OK);
	    }


}
