package com.yash.yotaapi.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.yash.yotaapi.util.ValidateRequestUtility;
import com.yash.yotaapi.util.ValidationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.yotaapi.dto.TechnologyDto;
import com.yash.yotaapi.services.IServices.ITechnologyService;
import com.yash.yotaapi.validators.IsTechnicalManager;

@RestController
@RequestMapping("/technology")
public class TechnologyController {

	@Autowired
	private ITechnologyService service;

	@Autowired
	private ValidateRequestUtility validateRequestUtility;

	@PostMapping("/addTechnology")
	@IsTechnicalManager
	public ResponseEntity<?> addTechnology(@RequestBody TechnologyDto technology) {

		Map<String, String> validationErrors = validateRequestUtility.validateRequest(technology);

		if (!validationErrors.isEmpty()){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors);
		}

		if (!ValidationUtility.isAlphabetic(technology.getTechnology())){
			validationErrors.put("name","technology name must be alphabetical");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors);
		}

		return ResponseEntity.status(HttpStatus.OK).body(this.service.addTechnology(technology));
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

	@PutMapping("/updateTechnology/{techId}")
	@IsTechnicalManager
	public ResponseEntity<?> updateTechnology(@PathVariable Long techId, @RequestBody TechnologyDto technology) {

		Map<String, String> validationErrors = validateRequestUtility.validateRequest(technology);

		if (!validationErrors.isEmpty()){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors);
		}

		if (!ValidationUtility.isAlphabetic(technology.getTechnology())){
			validationErrors.put("name","technology name must be alphabetical");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors);
		}

		return new ResponseEntity<>(this.service.updateTechnology(techId, technology), HttpStatus.OK);
	}
}
