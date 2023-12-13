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
import org.springframework.web.bind.annotation.RestController;

import com.yash.yotaapi.domain.Competency;
import com.yash.yotaapi.exception.CompetencyAlreadyExistsException;
import com.yash.yotaapi.service.CompetencyService;
import com.yash.yotaapi.util.FieldErrorValidationUtillity;

@CrossOrigin("*")
@RestController
@RequestMapping("/yota-api/competencies")
/**
 * Controller class for handling HTTP requests related to competency management.
 * This controller defines endpoints to perform operations such as adding,
 * retrieving, updating, and deleting competencies in the system.
 * 
 * @author gaurav.patil
 */
public class CompetencyController {

	/**
	 * Autowired field for interacting with the {@link CompetencyService}. This
	 * field is automatically injected by Spring, providing access to competency
	 * management operations.
	 */

	@Autowired
	CompetencyService competencyService;

	/* ValidationErroeMessageService is used to validate field error. */
	@Autowired
	FieldErrorValidationUtillity fileErrorValidationUtillity;

	/**
	 * Endpoint to add a new competency to the system. This method handles HTTP POST
	 * requests at the "/" endpoint. It accepts a JSON representation of the
	 * competency in the request body, adds the competency to the system, and
	 * returns a ResponseEntity containing the added competency and an HTTP status
	 * code.
	 
     * Before adding new competency it checks if a competency with the given name already exists in the competency_masters table.
     * If the competency does not exists, it is added to the table.If the competency already exists,
     * a {@link CompetencyAlreadyExistsException} is thrown.
	 * @param competency The competency details to be added (provided in the request
	 *                   body).
	 * @return A ResponseEntity with the added competency and an HTTP status code.
	 */
	@PostMapping("/")
	public ResponseEntity<?> addCompetency(@Valid @RequestBody Competency competency, BindingResult result) {
		ResponseEntity<?> errorMessage = fileErrorValidationUtillity.validationError(result);

		if (errorMessage != null)
			return errorMessage;
		else {
			if (!competencyService.getCompetencyByName(competency.getName())) {

				competencyService.addCompetency(competency);
				return new ResponseEntity<Competency>(competency, HttpStatus.CREATED);
			} else {
				throw new CompetencyAlreadyExistsException("Competency Already Exists...");
			}
		}

	}
	
	 /**
     * Endpoint to retrieve a list of all competency in the system.
     * This method handles HTTP GET requests at the "/" endpoint.
     *
     * @return A ResponseEntity with a list of all competency and an HTTP status code.
     */
	@GetMapping("/")
	public ResponseEntity<List<Competency>> getAllCompetency() {
		return new ResponseEntity<List<Competency>>(competencyService.getAllCompetency(), HttpStatus.OK);
	}

	/**
     * Endpoint to retrieve the details of a competency based on its ID.
     *
     * This method handles HTTP GET requests at the "/{id}" endpoint.
     * It takes the competency ID as a path variable, retrieves the details of the corresponding competency,
     * and returns a ResponseEntity containing the competency details and an HTTP status code.
     *
     * @param id The ID of the competency to be retrieved (provided as a path variable).
     * @return A ResponseEntity with the details of the requested competency and an HTTP status code.
     */
	@GetMapping("/{id}")
	public ResponseEntity<Competency> getCompetnecyById(@PathVariable(value = "id") long id) {
		return new ResponseEntity<Competency>(competencyService.getCompetencyById(id), HttpStatus.OK);
	}
	
	 /**
     * Endpoint to delete a competency from the system based on its ID.
     *
     * This method handles HTTP DELETE requests at the "/deleteCompetencytById/{id}" endpoint.
     * It takes the competency ID as a path variable, deletes the corresponding competency from the system,
     * and returns a ResponseEntity with a message and an HTTP status code.
     *
     * @param id The ID of the competency to be deleted (provided as a path variable).
     * @return A ResponseEntity with a deletion message and an HTTP status code.
     */
	@DeleteMapping("/deleteCompetencytById/{id}")
	public ResponseEntity<?> deleteCompetency(@PathVariable(value = "id") long id) {
		competencyService.deleteCompetencyById(id);
		return new ResponseEntity<String>("Competnecy of ID :" + id + " deleted...", HttpStatus.OK);
	}
	
	 /**
     * Endpoint to update the details of a competency in the system based on its ID.
     *
     * This method handles HTTP PUT requests at the "/updateCompetency/{id}" endpoint.
     * It takes the competency ID as a path variable and the updated competency details in the request body.
     * The method updates the competency with the specified ID and returns a ResponseEntity
     * containing the updated competency details and an HTTP status code.
     *
     * @param id The ID of the competency to be updated (provided as a path variable).
     * @param updatedCompetency The updated details for the competency (provided in the request body).
     * @return A ResponseEntity with the updated competency details and an HTTP status code.
     */
	@PutMapping("/updateCompetency/{id}")
	public ResponseEntity<?> updateCompetency(@PathVariable long id, @Valid @RequestBody Competency compentency) {
		Competency updatedComptency = competencyService.updateCompetency(id, compentency);
		return new ResponseEntity<>(updatedComptency, HttpStatus.OK);

	}

}
