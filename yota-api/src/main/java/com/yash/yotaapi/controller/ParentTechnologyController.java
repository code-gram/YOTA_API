package com.yash.yotaapi.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.yotaapi.domain.ParentTechnology;
import com.yash.yotaapi.service.ParentTechnologyService;
import com.yash.yotaapi.util.FieldErrorValidationUtillity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/** Parent Technology Controller will facilitates CRUD functionalities
 * @author pratik.kurbet
 *
 */
@CrossOrigin("*")
@Api(tags = "ParentTechnologyController",value = "Controller for Parent Technology")
@RestController
@RequestMapping("/yota/api/technologies")
public class ParentTechnologyController {

	/**
	 * ParentTechnologyService is used to interact controller layer with service layer.
	 */
	@Autowired
	private ParentTechnologyService parentTechnologyService;
	
	/**
	 * FieldErrorValidationService is used to validate field errors.
	 */
	@Autowired
	private FieldErrorValidationUtillity validationService;
	
	
	
	/**
	 * addParentTechnology method is used add ParentTechnology through service layer
	 * @param technology 
	 * @return generic type 
	 */
	@ApiOperation(tags ="Post Technology",value = "Add Technology")
	@PostMapping("/")
	public ResponseEntity<?> addParentTechnology(@Valid @RequestBody ParentTechnology technology, BindingResult result)
	{
		ResponseEntity<?> errorMap= validationService.validationError(result);
		if (errorMap!=null) {
			return errorMap;
		}
		return new ResponseEntity<ParentTechnology>(parentTechnologyService.save(technology),HttpStatus.OK);
	}
	
	
	/**
	 * getAll method is used to fetch all existing parent technology from DB
	 * @return List of ParentTechnology
	 */
	@ApiOperation(tags ="Get Technology",value = "Get All Technology")
	@GetMapping("/")
	public ResponseEntity<List<ParentTechnology>> getAll()
	{
		return new ResponseEntity<List<ParentTechnology>>(parentTechnologyService.getAllTechs(),HttpStatus.OK);
	}
	
	
	/**
	 * getTech method is used to get ParentTechnology from DB on basis name.
	 * @param name
	 * @return ParentTechnology
	 */
	@ApiOperation(tags ="Get Technology",value = "Get Technology by Name")
	@GetMapping("/{name}")
	public ResponseEntity<ParentTechnology> getTech(@PathVariable(value ="name") String name)
	{
		return new ResponseEntity<ParentTechnology>(parentTechnologyService.getTech(name),HttpStatus.OK);
	}
	
	
	/**
	 * searchTech method is used to get ParentTechnology from DB on basis Keyword.
	 * @param keyword
	 * @return List of ParentTechnology
	 */
	@ApiOperation(tags ="Get Technology",value = "Get Technology by Keyword")
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<ParentTechnology>> searchTech(@PathVariable("keyword") String keyword)
	{
		List<ParentTechnology> technologies=parentTechnologyService.searchTech(keyword);
		return new ResponseEntity<List<ParentTechnology>>(technologies,HttpStatus.OK);
	}
	
	
	/**
	 * removeTech method is used to delete ParentTechnology by id.
	 * @param id
	 * @return Generic type
	 */
	@ApiOperation(tags ="Delete Technology",value = "Delete Technology by Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeTech(@PathVariable(value = "id") long id)
	{
		 parentTechnologyService.removeTech(id);
		 return new ResponseEntity<String>("Technology with ID :"+id+" deleted.", HttpStatus.OK);
	}
	
	
	/**
	 * upadateTech method is used update the existing ParentTechnology from DB otherwise it will save one into DB
	 * @param technology
	 * @param result
	 * @return Updated ParentTechnology
	 */
	@ApiOperation(tags ="Update Technology",value = "Update Technology")
	@PutMapping("/")
	public ResponseEntity<?> upadateTech(@Valid @RequestBody ParentTechnology technology,BindingResult result)
	{
		ResponseEntity<?> errorMap= validationService.validationError(result);
		if (errorMap!=null) {
			return errorMap;
		}
		return new ResponseEntity<ParentTechnology>(parentTechnologyService.updateTech(technology),HttpStatus.OK);
	}
}
