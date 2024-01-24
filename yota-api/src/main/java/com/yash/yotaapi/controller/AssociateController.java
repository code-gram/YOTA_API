package com.yash.yotaapi.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.yash.yotaapi.domain.AssociateDetails;
import com.yash.yotaapi.service.AssociateDetailsService;
import com.yash.yotaapi.util.FieldErrorValidationUtillity;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Associate_Details_controller interact with service layer to complete the work
 * according to web request.
 *
 * @author RaghavMuchhal
 */
@CrossOrigin("*")
@Tag(name = "Associate Controller", description = "Controller for Associate")
@RestController
@RequestMapping("/associates")
public class AssociateController {
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
	 * @param principal // Add Principal parameter to access authenticated user
	 */

	@PostMapping("/register")
	public ResponseEntity<?> addAssociate(@Valid @RequestBody final AssociateDetails associate, BindingResult result,
			Principal principal) {
		ResponseEntity<?> errorMap = fieldErrorValidationService.validationError(result);
		if (errorMap != null) {
			return errorMap;
		}

		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		return new ResponseEntity<AssociateDetails>(associateDetailsService.selfRegister(associate), HttpStatus.OK);
	}

	/**
	 * This controller method handles the get request to access list of all
	 * associates.
	 */

	@GetMapping("/")
	public ResponseEntity<List<AssociateDetails>> getAll(Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		return new ResponseEntity<List<AssociateDetails>>(associateDetailsService.getAllAssociates(), HttpStatus.OK);
	}

	/**
	 * This controller method handles the delete the associate using id of
	 * associate.
	 *
	 * @param id
	 * @param principal // Add Principal parameter to access authenticated user
	 */

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAssociate(@PathVariable long id, Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		associateDetailsService.deleteAssociate(id);
		return new ResponseEntity<String>("Associate with id: " + id + " deleted successfully.", HttpStatus.OK);
	}

	/**
	 * This controller method handles the search request for associate using any
	 * keyword.
	 *
	 * @param keyword
	 * @param principal // Add Principal parameter to access authenticated user
	 */

	@GetMapping("/{keyword}/search")
	public ResponseEntity<List<AssociateDetails>> searchAssociate(@PathVariable String keyword, Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		List<AssociateDetails> associates = associateDetailsService.searchAssociate(keyword);
		return new ResponseEntity<List<AssociateDetails>>(associates, HttpStatus.OK);
	}

	 /**
     * This controller method handles the update request for details of an
     * associate.
     *
     * @param associate
     * @param result
     * @param id
     * @param principal // Add Principal parameter to access authenticated user
     */
	@PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "successfully updated")
    public ResponseEntity<?> updateAssociate(@Valid @RequestBody AssociateDetails associateDetails, BindingResult result,
            @PathVariable long id, Principal principal) {
        ResponseEntity<?> errorMap = fieldErrorValidationService.validationError(result);
        if (errorMap != null) {
            return errorMap;
        }
 
        // Access authenticated user's details (username)
        String username = principal.getName();
        // Add your logic here
 
        return new ResponseEntity<AssociateDetails>(associateDetailsService.updateAssociate(associateDetails, id),
                HttpStatus.OK);
    }

	 /**
     * This controller method handles the get request to get single associate.
     *
     * @param id
     * @param principal // Add Principal parameter to access authenticated user
     * @return
     */

	@GetMapping("/{id}")
    public ResponseEntity<Optional<AssociateDetails>> getAssociate(@PathVariable long id, Principal principal) {
        // Access authenticated user's details (username)
        String username = principal.getName();
        // Add your logic here
 
        return new ResponseEntity<Optional<AssociateDetails>>(associateDetailsService.getAssociate(id), HttpStatus.OK);
    }

	 /**
     * This controller method handles the update request for password only.
     *
     * @param updatePassword
     * @param principal // Add Principal parameter to access authenticated user
     * @return
     */
    @PostMapping("/update-password")
    public ResponseEntity<Boolean> updatePassword(@RequestBody HashMap<String, String> updatePassword,
            Principal principal) {
        // Access authenticated user's details (username)
        String username = principal.getName();
        // Add your logic here
 
        return new ResponseEntity<Boolean>(associateDetailsService.updatePassword(updatePassword), HttpStatus.OK);
    }
}
