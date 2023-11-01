package com.yash.yotaapi.controller;

import javax.validation.Valid;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yash.yotaapi.domain.TechnologyMaster;
import com.yash.yotaapi.service.TechnologyMasterService;
import com.yash.yotaapi.util.FieldErrorValidationUtillity;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.yash.yotaapi.util.FieldErrorValidationUtillity;

/**
 * Parent Technology Controller will facilitates CRUD functionalities
 *
 * @author pratik.kurbet
 */
@CrossOrigin("*")

@Tag(name = "Technology Controller", description = "Controller for Technology")

@RestController
@RequestMapping("/yota/api/technologies")
public class TechnologyMasterController {

	/**
	 * ParentTechnologyService is used to interact controller layer with service
	 * layer.
	 */
	@Autowired
	private TechnologyMasterService technologyMasterService;

	/**
	 * FieldErrorValidationService is used to validate field errors.
	 */
	@Autowired
	private FieldErrorValidationUtillity validationService;

	/**
	 * addParentTechnology method is used add ParentTechnology through service layer
	 *
	 * @param technology
	 * @return generic type
	 */

	@PostMapping("/")
	public ResponseEntity<?> addTechnology(@Valid @RequestBody TechnologyMaster technology, BindingResult result) {
		ResponseEntity<?> errorMap = validationService.validationError(result);
		if (errorMap != null) {
			return errorMap;
		}
		return new ResponseEntity<TechnologyMaster>(technologyMasterService.save(technology), HttpStatus.OK);
	}

	/**
	 * getAll method is used to fetch all existing parent technology from DB
	 *
	 * @return List of ParentTechnology
	 */

	@GetMapping("/")
	public ResponseEntity<List<TechnologyMaster>> getTechnologies() {
		return new ResponseEntity<List<TechnologyMaster>>(technologyMasterService.getAllTechs(), HttpStatus.OK);
	}

	/**
	 * getTech method is used to get ParentTechnology from DB on basis name.
	 *
	 * @param name
	 * @return ParentTechnology
	 */

	@GetMapping("/{id}")
	public ResponseEntity<TechnologyMaster> getTechnology(@PathVariable(value = "id") long id) {
		return new ResponseEntity<TechnologyMaster>(technologyMasterService.getTech(id), HttpStatus.OK);
	}

	/**
	 * searchTech method is used to get ParentTechnology from DB on basis Keyword.
	 *
	 * @param keyword
	 * @return List of ParentTechnology
	 */

	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<TechnologyMaster>> searchTechnology(@PathVariable("keyword") String keyword) {
		List<TechnologyMaster> technologies = technologyMasterService.searchTech(keyword);
		return new ResponseEntity<List<TechnologyMaster>>(technologies, HttpStatus.OK);
	}

	/**
	 * removeTech method is used to delete ParentTechnology by id.
	 *
	 * @param id
	 * @return Generic type
	 */

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeTechnology(@PathVariable(value = "id") long id) {
		technologyMasterService.removeTech(id);
		return new ResponseEntity<String>("Technology with ID :" + id + " deleted.", HttpStatus.OK);
	}

	/**
	 * upadateTech method is used update the existing ParentTechnology from DB
	 * otherwise it will save one into DB
	 *
	 * @param technology
	 * @param result
	 * @return Updated ParentTechnology
	 */

	@PutMapping("/{id}")
	@ApiResponse(responseCode = "200", description = "successfully updated")
	public ResponseEntity<?> updateTechnology(@Valid @RequestBody TechnologyMaster technology, BindingResult result,
			@PathVariable long id) {
		ResponseEntity<?> errorMap = validationService.validationError(result);
		if (errorMap != null) {
			return errorMap;
		}
		return new ResponseEntity<TechnologyMaster>(technologyMasterService.updateTech(technology, id), HttpStatus.OK);
	}
}
