package com.yash.yotaapi.service;

import com.yash.yotaapi.domain.Training;

import java.util.List;

/**
 * Service interface will perform business logic, method declaration for training.
 *
 * @author pravin.navarkar
 */
public interface TrainingService {

    /**
     * Adds a new training to the system.
     * This method is responsible for adding a new training with the provided details to the system.
     *
     * @param training The training details to be added.
     * @return The added training.
     */
    Training addTraining(Training training);

    /**
     * This method retrieves a list of all trainings from the table.
     *
     * @return A List containing all trainings in the system
     */
    List<Training> getAllTraining();

    /**
     * This method is responsible for deleting a training with the specified ID from the system.
     *
     * @param id The ID of the training to be deleted.
     */
    void deleteTrainingById(long id);

    /**
     * This method retrieves and returns the details of a training with the specified ID from the system.
     *
     * @param id The ID of the training to be retrieved.
     * @return The details of the training with the specified ID.
     */
    Training getTrainingById(long id);

    /**
     * This method attempts to find a training with the given ID in the system.
     *
     * @param id         The ID of the training to be updated.
     * @param training The updated details for the training.
     * @return The updated training.
     */
    Training updateTraining(long id, Training training);

    /**
     * Validates a Training object to ensure it meets the required criteria.
     *
     * @param training The Training object to be validated.
     * @return The validated Training object if validation is successful.
     */
    Training validateTraining(Training training);
}
