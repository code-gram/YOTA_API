package com.yash.yotaapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import com.yash.yotaapi.domain.TechnologyMaster;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.yash.yotaapi.domain.AssociateDetails;
import com.yash.yotaapi.service.AssociateDetailsService;
import com.yash.yotaapi.util.FieldErrorValidationUtillity;

import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * Associate_Details_controller interact with service layer to complete the work
 * according to web request.
 * 
 * @author nitin.chougale
 */
@CrossOrigin("*")

@Tag(name="Associate Controller", description="Controller for Associate")

@RestController
@RequestMapping("/yota/api/associates")

public class AssociateDetailsController {

	@Autowired
	private AssociateDetailsService associateDetailsService;

	@Autowired
	FieldErrorValidationUtillity fieldErrorValidationService;

	/**
	 * This controller method handles the HTTP POST request of associate for self
	 * registration, matching with the given URI.
	 * 
	 * @param associate
	 * @param result
	 */

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
	@PutMapping("/{id}")
	@ApiResponse(responseCode = "200", description = "successfully updated")
	public ResponseEntity<?> updateAssociate(@Valid @RequestBody AssociateDetails associateDetails, BindingResult result,
											  @PathVariable long id) {
		ResponseEntity<?> errorMap = fieldErrorValidationService.validationError(result);
		if (errorMap != null) {
			return errorMap;
		}
		return new ResponseEntity<AssociateDetails>(associateDetailsService.updateAssociate(associateDetails, id), HttpStatus.OK);
	}
	
	/**
	     * This controller method handles the get request to get single 
	     * associate.
	     * @param id
	     * @return
	     */
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<AssociateDetails>> getAssociate(@PathVariable long id)
	{
	return new ResponseEntity<Optional<AssociateDetails>>(associateDetailsService.getAssociate(id), HttpStatus.OK);
	}

	/**
	 * This controller method handles the update request for password only.
	 * @param updatePassword
	 * @return
	 */

	@PostMapping("/updatePassword")
	public ResponseEntity<Boolean> updatepassword(@RequestBody HashMap<String, String> updatePassword)
	{
		return new ResponseEntity<Boolean>(associateDetailsService.updatePassword(updatePassword),HttpStatus.OK);
		}

}
