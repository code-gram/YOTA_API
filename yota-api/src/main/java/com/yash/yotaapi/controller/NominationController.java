package com.yash.yotaapi.controller;
 
import com.yash.yotaapi.domain.Nomination;
import com.yash.yotaapi.service.NominationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import javax.validation.Valid;
import java.util.List;
 
/**
 * Nomination Controller 
 * @author raghav.muchhal
 * 
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
    public ResponseEntity<Nomination> createNomination(@Valid @RequestBody Nomination nomination) {
        Nomination createdNomination = nominationService.createNomination(nomination);
        return new ResponseEntity<>(createdNomination, HttpStatus.CREATED);
    }
 
    /**
     * Get all nominations.
     *
     * @return List of all nominations.
     */
    @GetMapping
    public ResponseEntity<List<Nomination>> getAllNominations() {
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
    public ResponseEntity<Nomination> getNominationById(@PathVariable Long nominationId) {
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
    public ResponseEntity<Nomination> updateNomination(@PathVariable Long nominationId, @Valid @RequestBody Nomination nominationDetails) {
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
    public ResponseEntity<Void> deleteNomination(@PathVariable Long nominationId) {
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
    public ResponseEntity<List<Nomination>> searchByName(@PathVariable String employeeName) {
        List<Nomination> nominations = nominationService.findByEmployeeName(employeeName);
        if (nominations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(nominations, HttpStatus.OK);
    }
}