package com.yash.yotaapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.yotaapi.domain.AssociateDetails;
import com.yash.yotaapi.service.AssociateDetailsService;
import com.yash.yotaapi.util.FieldErrorValidationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Associate_Details_controller interact with service layer to complete the work according to web request.
 *
 */
@Api(tags = "AssociateDetailsController", value = "Controller of Associate details")
@RestController
@RequestMapping("/yota/associate_details")
public class AssociateDetailsController {
	
	@Autowired
	private AssociateDetailsService associateDetailsService;
	
	@Autowired
	FieldErrorValidationService fieldErrorValidationService;
	
	/**
	 * This controller method handles the HTTP POST request of associate, matching with the given URI.
	 */
	@ApiOperation(tags = "Post Associate", value = "Add Associate")
	@PostMapping("/")
	public ResponseEntity<?> addAssociate(@Valid @RequestBody AssociateDetails associate, BindingResult result)
	{
		ResponseEntity<?> errorMap = fieldErrorValidationService.validationError(result);
		if (errorMap!=null) {
			return errorMap;
		}
		return new ResponseEntity<AssociateDetails>(associateDetailsService.selfRegister(associate),HttpStatus.OK);
	}
}

