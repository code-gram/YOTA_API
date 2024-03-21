package com.yash.yotaapi.controller;

import com.yash.yotaapi.domain.Nomination;
import com.yash.yotaapi.service.NominationService;
import com.yash.yotaapi.util.ExcelHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Nomination Controller
 * 
 * @author purv.baraskar
 */
@RestController
@RequestMapping("/nominations")
public class NominationController {

	private final NominationService nominationService;

	@Autowired
	public NominationController(NominationService nominationService) {
		this.nominationService = nominationService;
	}

	/**
	 * Create a new nomination.
	 *
	 * @param nomination The nomination object to be created.
	 * @return The created nomination object.
	 */
	@PostMapping
	public ResponseEntity<String> createNomination(@Valid @RequestBody List<Nomination> nominations, Principal principal) {
		String username = principal.getName();
		try {
			nominationService.createNomination(nominations);
			System.out.println("List of nominations:"+ nominations);
			return new ResponseEntity<>("Nominations added successfully",HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Get all nominations.
	 *
	 * @return List of all nominations.
	 */
	@GetMapping
	public ResponseEntity<List<Nomination>> getAllNominations(Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		List<Nomination> nominations = nominationService.getAllNominations();
		if (nominations.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(nominations, HttpStatus.OK);
	}

	/**
	 * Get a nomination by ID.
	 *
	 * @param nominationId The ID of the nomination to retrieve.
	 * @return The nomination with the given ID.
	 */
	@GetMapping("/{nominationId}")
	public ResponseEntity<Nomination> getNominationById(@PathVariable Long nominationId, Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		Nomination nomination = nominationService.getNominationById(nominationId);
		return new ResponseEntity<>(nomination, HttpStatus.OK);
	}

	/**
	 * Update a nomination by ID.
	 *
	 * @param nominationId      The ID of the nomination to update.
	 * @param nominationDetails The updated details for the nomination.
	 * @return The updated nomination object.
	 */
	@PutMapping("/{nominationId}")
	public ResponseEntity<Nomination> updateNomination(@PathVariable Long nominationId,
			@Valid @RequestBody Nomination nominationDetails, Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		Nomination updatedNomination = nominationService.updateNomination(nominationId, nominationDetails);
		return new ResponseEntity<>(updatedNomination, HttpStatus.OK);
	}

	/**
	 * Delete a nomination by ID.
	 *
	 * @param nominationId The ID of the nomination to delete.
	 * @return No content on successful deletion.
	 */
	@DeleteMapping("/{nominationId}")
	public ResponseEntity<Void> deleteNomination(@PathVariable Long nominationId, Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		nominationService.deleteNomination(nominationId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Search nominations by employee name.
	 *
	 * @param employeeName The name of the employee to search for nominations.
	 * @return List of nominations for the given employee name.
	 */
	@GetMapping("/searchByName/{employeeName}")
	public ResponseEntity<List<Nomination>> searchByName(@PathVariable String employeeName, Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		List<Nomination> nominations = nominationService.findByEmployeeName(employeeName);
		if (nominations.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(nominations, HttpStatus.OK);
	}
	
	@PostMapping("/bulkNominations")
	public ResponseEntity<?> uploadExcelFile(@RequestParam("file") MultipartFile file, Principal principal) {
        
        if (ExcelHelper.checkExcelFormat(file)) {
            nominationService.saveExcel(file);
            return new ResponseEntity<String>("Excel File Uploaded Successfully", HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload Excel File only.");
    }
}