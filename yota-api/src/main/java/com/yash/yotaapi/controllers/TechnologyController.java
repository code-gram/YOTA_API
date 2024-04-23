package com.yash.yotaapi.controllers;

import com.yash.yotaapi.dto.TechnologyDto;
import com.yash.yotaapi.services.IServices.ITechnologyService;
import com.yash.yotaapi.validators.IsTechnicalManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/technology")
public class TechnologyController {

    @Autowired
    private ITechnologyService service;


    @PostMapping("/addTechnology")
    @IsTechnicalManager
    public ResponseEntity<TechnologyDto> addTechnology(@RequestBody TechnologyDto technology
    ) {
        return new ResponseEntity<>(this.service.addTechnology(technology), HttpStatus.OK);
    }

	 	/**
	     * getAll method is used to fetch all existing parent technology from DB
	     *
	     * @return List of ParentTechnology
	     */
	    @GetMapping("/")
	    public ResponseEntity<List<TechnologyDto>> fetchAllTechnology() {
	        return new ResponseEntity<List<TechnologyDto>>(service.fetchAllTechnology(), HttpStatus.OK);
	    }
}
