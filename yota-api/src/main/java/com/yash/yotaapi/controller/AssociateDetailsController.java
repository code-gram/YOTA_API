package com.yash.yotaapi.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.yotaapi.domain.AssociateDetails;
import com.yash.yotaapi.service.AssociateDetailsService;
import com.yash.yotaapi.util.FieldErrorValidationUtility;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Associate_Details_controller interact with service layer to complete the work
 * according to web request.
 * 
 * @author nitin.chougale
 */
@Api(tags = "AssociateDetailsController", value = "Controller of Associate details")
@RestController
@RequestMapping("/yota/api/associates")
public class AssociateDetailsController {

	@Autowired
	private AssociateDetailsService associateDetailsService;

	@Autowired
	FieldErrorValidationUtility fieldErrorValidationService;

	/**
	 * This controller method handles the HTTP POST request of associate for self
	 * registration, matching with the given URI.
	 * 
	 * @param associate
	 * @param result
	 */
	@ApiOperation(tags = "Register Associate", value = "Add Associate")
	@PostMapping("/register")
	public ResponseEntity<?> addAssociate(@Valid @RequestBody final AssociateDetails associate, BindingResult result) {
		ResponseEntity<?> errorMap = fieldErrorValidationService.validationError(result);
		if (errorMap != null) {
			return errorMap;
		}
		return new ResponseEntity<AssociateDetails>(associateDetailsService.selfRegister(associate), HttpStatus.OK);
	}

	/**
	 * This controller method handles the get request to access list of all
	 * associates.
	 */
	@ApiOperation(tags = "Get All Associates", value = "List of Associates")
	@GetMapping("/all")
	public ResponseEntity<List<AssociateDetails>> getAll() {
		return new ResponseEntity<List<AssociateDetails>>(associateDetailsService.getAllAssociates(), HttpStatus.OK);
	}

	/**
	 * This controller method handles the delete the associate using id of
	 * associate.
	 * 
	 * @param id
	 */
	@ApiOperation(tags = "Delete Associate", value = "Delete Associate")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteAssociate(@PathVariable long id) {
		associateDetailsService.deleteAssociate(id);
		return new ResponseEntity<String>("Associate with id: " + id + " deleted successfully.", HttpStatus.OK);
	}

	/**
	 * This controller method handles the search request for associate using any
	 * keyword.
	 * 
	 * @param keyword
	 */

	@ApiOperation(tags= "Free text search", value = "Seacrh associate using keyword")
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<AssociateDetails>> searchAssociate(@PathVariable String keyword) {
		List<AssociateDetails> associates = associateDetailsService.searchAssociate(keyword);
		return new ResponseEntity<List<AssociateDetails>>(associates, HttpStatus.OK);
	}

	/**
	 * This controller method handles the update request for details of an associate.
	 * @param associate
	 * @param result
	 */
	@ApiOperation(tags = "Update Associate", value = "Update Associate")
	@PostMapping("/")
	public ResponseEntity<?> updateAssociate(@RequestBody AssociateDetails associate,BindingResult result)
	{
		ResponseEntity<?> errorMap = fieldErrorValidationService.validationError(result);
		if(errorMap!=null) {
			return errorMap;
		}
		return new ResponseEntity<AssociateDetails>(associateDetailsService.updateAssociate(associate), HttpStatus.OK);
	}

	/**
	 * This controller method handles the update request for password only.
	 * @param updatePassword
	 * @return
	 */
	@ApiOperation(tags = "Update Associate Password", value = "Update Associate password")
	@PostMapping("/updatePassword")
	public ResponseEntity<Boolean> updatepassword(@RequestBody HashMap<String, String> updatePassword)
	{
		return new ResponseEntity<Boolean>(associateDetailsService.updatePassword(updatePassword),HttpStatus.OK);
		}
}
