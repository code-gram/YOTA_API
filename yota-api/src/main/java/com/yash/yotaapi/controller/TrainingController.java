package com.yash.yotaapi.controller;

import com.yash.yotaapi.domain.Training;
import com.yash.yotaapi.exception.TrainingAlreadyExistsException;
import com.yash.yotaapi.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller class for handling HTTP requests related to training management.
 * This class defines endpoints to perform operations such as adding, retrieving,
 * updating, and deleting trainings in the system.
 *
 * @author pravin.navarkar
 */
@RestController
@RequestMapping("/yota-api/trainingmasters")
public class TrainingController {

    /**
     * Autowired field for interacting with the {@link TrainingService}.
     * This field is automatically injected by Spring, providing access to training management operations.
     */
    @Autowired
    private TrainingService trainingService;

    /**
     * Endpoint to add a new training to the system.
     * <p>
     * This method handles HTTP POST requests at the "/addTraining" endpoint.
     * It accepts a JSON representation of the training in the request body, adds the training to the system,
     * and returns a ResponseEntity containing the added training and an HTTP status code.
     *
     * @param training The training details to be added (provided in the request body).
     * @return A ResponseEntity with the added training and an HTTP status code.
     */
    @PostMapping("/")
    public ResponseEntity<?> addTraining(@Valid @RequestBody Training training) {
        Training returnedTraining = null;
        if (trainingService.validateTraining(training) == null) {
            returnedTraining = trainingService.addTraining(training);
        } else {
            throw new TrainingAlreadyExistsException("Training already exists!!");
        }
        return new ResponseEntity<>(returnedTraining, HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve a list of all training in the system.
     * This method handles HTTP GET requests at the "/getAllTraining" endpoint.
     *
     * @return A ResponseEntity with a list of all trainings and an HTTP status code.
     */
    @GetMapping("/")
    public ResponseEntity<?> getAllTraining() {
        return new ResponseEntity<>(trainingService.getAllTraining(), HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve the details of a training based on its ID.
     * <p>
     * This method handles HTTP GET requests at the "/getTrainingById/{id}" endpoint.
     * It takes the training ID as a path variable, retrieves the details of the corresponding training,
     * and returns a ResponseEntity containing the training details and an HTTP status code.
     *
     * @param id The ID of the training to be retrieved (provided as a path variable).
     * @return A ResponseEntity with the details of the requested training and an HTTP status code.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getTraining(@PathVariable(value = "id") long id) {
        return new ResponseEntity<>(trainingService.getTrainingById(id), HttpStatus.OK);
    }

    /**
     * Endpoint to delete a training from the system based on its ID.
     * <p>
     * This method handles HTTP DELETE requests at the "/deleteTrainingById/{id}" endpoint.
     * It takes the training ID as a path variable, deletes the corresponding training from the system,
     * and returns a ResponseEntity with a message and an HTTP status code.
     *
     * @param id The ID of the training to be deleted (provided as a path variable).
     * @return A ResponseEntity with a deletion message and an HTTP status code.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeTraining(@PathVariable(value = "id") long id) {
        trainingService.deleteTrainingById(id);
        return new ResponseEntity<>("Training with ID :" + id + " deleted.", HttpStatus.OK);
    }

    /**
     * Endpoint to update the details of a training in the system based on its ID.
     * <p>
     * This method handles HTTP PUT requests at the "/{id}" endpoint.
     * It takes the training ID as a path variable and the updated training details in the request body.
     * The method updates the training with the specified ID and returns a ResponseEntity
     * containing the updated training details and an HTTP status code.
     *
     * @param id                The ID of the training to be updated (provided as a path variable).
     * @param updatedTraining The updated details for the training (provided in the request body).
     * @return A ResponseEntity with the updated training details and an HTTP status code.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTraining(@PathVariable long id, @RequestBody Training updatedTraining) {
        Training updatedTrainingDetails = trainingService.updateTraining(id, updatedTraining);
        return new ResponseEntity<>(updatedTrainingDetails, HttpStatus.OK);
    }
}

