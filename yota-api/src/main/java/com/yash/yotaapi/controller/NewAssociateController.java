package com.yash.yotaapi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yash.yotaapi.domain.NewAssociateDetail;
import com.yash.yotaapi.domain.Training;
import com.yash.yotaapi.service.NewAssociateDetailsService;
import com.yash.yotaapi.util.ExcelHelper;
import com.yash.yotaapi.util.FieldErrorValidationUtillity;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Associate_Details_controller interact with service layer to complete the work
 * according to web request.
 *
 *
 * @author Vishal Kanthariya
 */
@CrossOrigin("*")
@Tag(name = "Associate Controller", description = "Controller for Associate")
@RestController
@RequestMapping("/newAssociates")
public class NewAssociateController {
	
	@Autowired
	private NewAssociateDetailsService newAssociateDetailsService;

	@Autowired
	FieldErrorValidationUtillity fieldErrorValidationService;
	
	/**
	 * This controller method handles the get request to access list of all
	 * associates.
	 */

	@GetMapping("{id}")
	public ResponseEntity<List<NewAssociateDetail>> getAllAssociates(@PathVariable long id ,Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here
		return new ResponseEntity<List<NewAssociateDetail>>(newAssociateDetailsService.getAllAssociates(id), HttpStatus.OK);
	}

	
	@PostMapping("/bulkAddAssociates")
	public ResponseEntity<?> uploadExcelFile(@RequestParam("id") long id ,@RequestParam("file") MultipartFile file, Principal principal) {
        if (ExcelHelper.checkExcelFormat(file)) {
        	newAssociateDetailsService.saveExcel(file,id);
            return new ResponseEntity<String>("Excel File Uploaded Successfully", HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload Excel File only.");
    }

}
