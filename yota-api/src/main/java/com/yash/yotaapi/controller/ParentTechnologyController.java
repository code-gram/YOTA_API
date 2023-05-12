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
import com.yash.yotaapi.domain.ParentTechnology;
import com.yash.yotaapi.service.ParentTechnologyService;
import com.yash.yotaapi.util.FieldErrorValidationUtillity;
import io.swagger.v3.oas.annotations.tags.Tag;

/** Parent Technology Controller will facilitates CRUD functionalities
 * @author pratik.kurbet
 *
 */
@CrossOrigin("*")
@Tag(name="Technology Controller", description="Controller for Technology")
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
