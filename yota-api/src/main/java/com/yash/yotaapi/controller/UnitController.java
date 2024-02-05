package com.yash.yotaapi.controller;

import com.yash.yotaapi.domain.Unit;
import com.yash.yotaapi.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Controller class for handling HTTP requests related to unit management.
 * This class defines endpoints to perform operations such as adding, retrieving,
 * updating, and deleting Unit in the system.
 *
 * @author pravin.navarkar
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/units")
public class UnitController {

    /**
     * Autowired field for interacting with the {@link UnitService}.
     * This field is automatically injected by Spring, providing access to unit management operations.
     */
    @Autowired
    private UnitService unitService;

    /**
     * Endpoint to add a new unit to the system.
     * <p>
     * This method handles HTTP POST requests at the "/addUnit" endpoint.
     * It accepts a JSON representation of the unit in the request body, adds the unit to the system,
     * and returns a ResponseEntity containing the added unit and an HTTP status code.
     *
     * @param unit The unit details to be added (provided in the request body).
     * @return A ResponseEntity with the added unit and an HTTP status code.
     */
    @PostMapping("/")
    public ResponseEntity<?> addUnit(@Valid @RequestBody Unit unit, Principal principal) {
        String username = principal.getName();
        return new ResponseEntity<Unit>(unitService.addUnit(unit), HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve a list of all Unit in the system.
     * This method handles HTTP GET requests at the "/getAllUnit" endpoint.
     *
     * @return A ResponseEntity with a list of all Unit and an HTTP status code.
     */
    @GetMapping("/")
    public ResponseEntity<List<Unit>> getAllUnit() {
        return new ResponseEntity<List<Unit>>(unitService.getAllUnit(), HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve the details of a unit based on its ID.
     *
     * This method handles HTTP GET requests at the "/getUnitById/{id}" endpoint.
     * It takes the unit ID as a path variable, retrieves the details of the corresponding unit,
     * and returns a ResponseEntity containing the unit details and an HTTP status code.
     *
     * @param id The ID of the unit to be retrieved (provided as a path variable).
     * @return A ResponseEntity with the details of the requested unit and an HTTP status code.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUnit(@PathVariable(value = "id") long id) {
        return new ResponseEntity<Unit>(unitService.getUnitById(id), HttpStatus.OK);
    }

    /**
     * Endpoint to delete a unit from the system based on its ID.
     *
     * This method handles HTTP DELETE requests at the "/deleteUnitById/{id}" endpoint.
     * It takes the unit ID as a path variable, deletes the corresponding unit from the system,
     * and returns a ResponseEntity with a message and an HTTP status code.
     *
     * @param id The ID of the unit to be deleted (provided as a path variable).
     * @return A ResponseEntity with a deletion message and an HTTP status code.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeUnit(@PathVariable(value = "id") long id) {
        unitService.deleteUnitById(id);
        return new ResponseEntity<String>("Unit with ID :" + id + " deleted.", HttpStatus.OK);
    }

    /**
     * Endpoint to update the details of a unit in the system based on its ID.
     *
     * This method handles HTTP PUT requests at the "/updateUnitById/{id}" endpoint.
     * It takes the unit ID as a path variable and the updated unit details in the request body.
     * The method updates the unit with the specified ID and returns a ResponseEntity
     * containing the updated unit details and an HTTP status code.
     *
     * @param id The ID of the unit to be updated (provided as a path variable).
     * @param updatedUnitMaster The updated details for the unit (provided in the request body).
     * @return A ResponseEntity with the updated unit details and an HTTP status code.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUnit(@PathVariable long id, @RequestBody Unit updatedUnitMaster,Principal principal) {
        String username = principal.getName();
        Unit updatedUnit = unitService.updateUnit(id, updatedUnitMaster);
        return new ResponseEntity<>(updatedUnit, HttpStatus.OK);
    }

    /**
     * Search unit by a name.
     *
     * @param unitName The unitName to search for in unit details.
     * @return unit matching the name.
     */
    @GetMapping("/search/{unitName}")
    public ResponseEntity<?> searchUnitByName(@PathVariable("unitName") String unitName){
        Unit units = unitService.searchUnit(unitName);
        return new ResponseEntity<>(units, HttpStatus.OK);
    }
}
