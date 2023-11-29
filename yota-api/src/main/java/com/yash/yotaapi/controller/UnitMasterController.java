package com.yash.yotaapi.controller;

import com.yash.yotaapi.domain.UnitMaster;
import com.yash.yotaapi.service.UnitMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to unit management.
 * This class defines endpoints to perform operations such as adding, retrieving,
 * updating, and deleting units in the system.
 *
 * @author pravin.navarkar
 */
@RestController
@RequestMapping("/yota/api/unitmaster")
public class UnitMasterController {

    /**
     * Autowired field for interacting with the {@link UnitMasterService}.
     * This field is automatically injected by Spring, providing access to unit management operations.
     */
    @Autowired
    private UnitMasterService unitService;

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
    @PostMapping("/addUnit")
    public ResponseEntity<UnitMaster> addUnit(@RequestBody UnitMaster unit) {
        return new ResponseEntity<UnitMaster>(unitService.addUnit(unit), HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve a list of all units in the system.
     * This method handles HTTP GET requests at the "/getAllUnit" endpoint.
     *
     * @return A ResponseEntity with a list of all units and an HTTP status code.
     */
    @GetMapping("/getAllUnit")
    public ResponseEntity<List<UnitMaster>> getAllUnit() {
        return new ResponseEntity<List<UnitMaster>>(unitService.getAllUnit(), HttpStatus.OK);
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
    @GetMapping("/getUnitById/{id}")
    public ResponseEntity<UnitMaster> getUnit(@PathVariable(value = "id") long id) {
        return new ResponseEntity<UnitMaster>(unitService.getUnitById(id), HttpStatus.OK);
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
    @DeleteMapping("/deleteUnitById/{id}")
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
    @PutMapping("/updateUnitById/{id}")
    public ResponseEntity<UnitMaster> updateUnit(@PathVariable long id, @RequestBody UnitMaster updatedUnitMaster) {
        UnitMaster updatedUnit = unitService.updateUnit(id, updatedUnitMaster);
        return new ResponseEntity<>(updatedUnit, HttpStatus.OK);
    }
}
