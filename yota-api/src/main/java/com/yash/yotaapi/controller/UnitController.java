package com.yash.yotaapi.controller;

import java.security.Principal;
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

import com.yash.yotaapi.domain.Unit;
import com.yash.yotaapi.service.UnitService;
import com.yash.yotaapi.util.FieldErrorValidationUtillity;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
* Parent Unit Controller will facilitates CRUD functionalities
*
*@author chandana.nemade
*/
@CrossOrigin("*")
@Tag(name = "Unit Controller", description = "Controller for Unit")
@RestController
@RequestMapping("/units")
public class UnitController {
	/**
     * ParentUnitService is used to interact controller layer with service
     * layer.
     */
    private final UnitService unitService;
 
    /**
     * FieldErrorValidationService is used to validate field errors.
     */
    @Autowired
    private FieldErrorValidationUtillity validationService;

    UnitController(UnitService unitService) {
        this.unitService = unitService;
    }
 
    /**
     * addParentUnit method is used add ParentUnit through service layer
     *
     * @param unit
     * @return generic type
     */
    @PostMapping("/")
    public ResponseEntity<?> addUnit(@Valid @RequestBody Unit unit, BindingResult result,
            Principal principal) {
        // Access authenticated user's details (username)
        String username = principal.getName();
        // Add your logic here
 
        ResponseEntity<?> errorMap = validationService.validationError(result);
        if (errorMap != null) {
            return errorMap;
        }
        return new ResponseEntity<Unit>(unitService.save(unit), HttpStatus.OK);
    }
 
    /**
     * getAll method is used to fetch all existing parent unit from DB
     *
     * @return List of ParentUnit
     */
    @GetMapping("/")
    public ResponseEntity<List<Unit>> getUnits() {
    	List<Unit> units = unitService.getAllUnits();
        return new ResponseEntity<List<Unit>>(units, HttpStatus.OK);
    
//      redux  return new ResponseEntity<List<Unit>>(unitService.getAllUnits(), HttpStatus.OK);
    }
 
    /**
     * getUnit method is used to get ParentUnit from DB on basis name.
     *
     * @param id
     * @return ParentUnit
     */
    @GetMapping("/{id}")
    public ResponseEntity<Unit> getUnit(@PathVariable(value = "id") long id) {
        return new ResponseEntity<Unit>(unitService.getUnit(id), HttpStatus.OK);
    }
 
    /**
     * searchUnit method is used to get ParentUnit from DB on basis Keyword.
     *
     * @param keyword
     * @return List of ParentUnit
     */
    @GetMapping("/{keyword}/search")
    public ResponseEntity<List<Unit>> searchUnit(@PathVariable("keyword") String keyword) {
        List<Unit> units = unitService.searchUnit(keyword);
        return new ResponseEntity<List<Unit>>(units, HttpStatus.OK);
    }
 
    /**
     * removeUnit method is used to delete ParentUnit by id.
     *
     * @param id
     * @return Generic type
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeUnit(@PathVariable(value = "id") long id) {
        unitService.removeUnit(id);
        return new ResponseEntity<String>("Unit with ID :" + id + " deleted.", HttpStatus.OK);
    }
 
    /**
     * upadateUnit method is used update the existing ParentUnit from DB
     * otherwise it will save one into DB
     *
     * @param unit
     * @param result
     * @return Updated ParentUnit
     */
    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "successfully updated")
    public ResponseEntity<?> updateUnit(@Valid @RequestBody Unit unit, BindingResult result,
            Principal principal, @PathVariable long id) {
        // Access authenticated user's details (username)
        String username = principal.getName();
        // Add your logic here
 
        ResponseEntity<?> errorMap = validationService.validationError(result);
        if (errorMap != null) {
            return errorMap;
        }
        return new ResponseEntity<Unit>(unitService.updateUnit(unit, id), HttpStatus.OK);
    }

}
