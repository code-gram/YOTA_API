package com.yash.yotaapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.yotaapi.domain.AssociateDetails;
import com.yash.yotaapi.service.AssociateDetailsService;
import com.yash.yotaapi.util.FieldErrorValidationUtillity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Associate_Details_controller interact with service layer to complete the work according to web request.
 * @author nitin.chougale
 */
@Api(tags = "AssociateDetailsController", value = "Controller of Associate details")
@RestController
@RequestMapping("/yota/associate")
public class AssociateDetailsController {
	
	@Autowired
	private AssociateDetailsService associateDetailsService;
	
	@Autowired
	FieldErrorValidationUtillity fieldErrorValidationService;
	
	/**
	 * This controller method handles the HTTP POST request of associate for self registration, matching with the given URI.
	 * @param associate
	 * @param result
	 */
	@ApiOperation(tags = "Register Associate", value = "Add Associate")
	@PostMapping("/add")
	public ResponseEntity<?> addAssociate(@Valid @RequestBody AssociateDetails associate, BindingResult result)
	{
		ResponseEntity<?> errorMap = fieldErrorValidationService.validationError(result);
		if (errorMap!=null) {
			return errorMap;
		}
		return new ResponseEntity<AssociateDetails>(associateDetailsService.selfRegister(associate),HttpStatus.OK);
	}
	
	/**
	 * This controller method handles the get request to access list of all associates.
	 */
	@ApiOperation(tags = "Get All Associates", value = "List of Associates")
	@GetMapping("/all")
	public ResponseEntity<List<AssociateDetails>> getAll()
	{
		return new ResponseEntity<List<AssociateDetails>>(associateDetailsService.getAllAssociates(),HttpStatus.OK);
	}
	
	/**
	 * This controller method handles the delete the associate using id of associate.
	 * @param id
	 */
	@ApiOperation(tags = "Delete Associate", value = "Delete Associate")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteAssociate(@PathVariable long id)
	{
		associateDetailsService.deleteAssociate(id);
		return new ResponseEntity<String>("Associate with id: "+id+" deleted successfully.", HttpStatus.OK);
	}
	
	/**
	 * This controller method handles the search request for associate using any keyword.
	 * @param keyword
	 */
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<AssociateDetails>> searchAssociate(@PathVariable String keyword)
	{
		List<AssociateDetails> associates = associateDetailsService.searchAssociate(keyword);
		return new ResponseEntity<List<AssociateDetails>>(associates, HttpStatus.OK);
	}
	
	/**
	 * This controller method handles the update request for details of an associate.
	 * @param associate
	 * @param result
	 */
	@ApiOperation(tags = "Update Associate", value = "Update Associate")
	@PutMapping("/")
	public ResponseEntity<?> updateAssociate(@Valid @RequestBody AssociateDetails associate,BindingResult result)
	{
		ResponseEntity<?> errorMap = fieldErrorValidationService.validationError(result);
		if(errorMap!=null) {
			return errorMap;
		}
		return new ResponseEntity<AssociateDetails>(associateDetailsService.updateAssociate(associate), HttpStatus.OK);
	}

	/**
	 * This controller method accepts the request of searching for associate using id.
	 * @param id
	 */
	@ApiOperation(tags = "Get Associate", value = "Get searched Associate")
	@GetMapping("/{id}")
	public ResponseEntity<AssociateDetails> getAssociate(@PathVariable long id)
	{
		return new ResponseEntity<AssociateDetails>(associateDetailsService.getAssociate(id), HttpStatus.FOUND);
	}	
}

