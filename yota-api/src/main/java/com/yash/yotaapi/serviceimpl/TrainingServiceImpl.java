package com.yash.yotaapi.serviceimpl;

import com.yash.yotaapi.domain.Training;
import com.yash.yotaapi.exception.TrainingNotFoundException;
import com.yash.yotaapi.repository.TrainingRepository;
import com.yash.yotaapi.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link TrainingService} interface.
 * This class provides business logic related to the management of trainings in the system.
 *
 * @author pravin.navarkar
 */
@Service
public class TrainingServiceImpl implements TrainingService {

    /**
     * Autowired instance of the TrainingRepository for accessing training-related data in the system.
     *
     * <p>The {@code trainingRepository} field is annotated with {@code @Autowired}, indicating that
     * Spring will automatically inject an instance of {@code TrainingRepository} into this field
     * during bean initialization. This allows the service to interact with the underlying data
     * store to perform operations related to training entities.
     *
     * <p>It is assumed that the {@code TrainingRepository} interface provides methods for
     * CRUD (Create, Read, Update, Delete) operations on training entities.
     *
     * @see TrainingRepository
     */
    @Autowired
    private TrainingRepository trainingRepository;

    /**
     * Adds a new training to the training masters table.
     * <p>
     * This method checks if a training with the given ID already exists in the training masters table.
     * If the training does not exists, it is added to the table.If the training already exists,
     * a {@link com.yash.yotaapi.exception.TrainingAlreadyExistsException} is thrown.
     *
     * @param training The training to be added to the training masters table.
     * @return The added training if it does not already exists.
     * @throws com.yash.yotaapi.exception.TrainingAlreadyExistsException If the training with the same ID already exists in the table.
     */
    @Override
    public Training addTraining(Training training) {
        return trainingRepository.save(training);
    }

    /**
     * This method retrieves a list of all trainings stored in the training masters table.
     *
     * @return A list containing all trainings from the training masters table.
     */
    @Override
    public List<Training> getAllTraining() {
        return trainingRepository.findAll();
    }

    /**
     * Deletes a training from the training masters table based on its ID.
     * This method attempts to find a training with the given ID in the training masters table.
     * If the training is found, it is deleted. If the training is not found, a {@link TrainingNotFoundException}
     * is thrown.
     *
     * @param id The ID of the training to be deleted.
     * @throws TrainingNotFoundException If a training with the specified ID is not found in the table.
     */
    @Override
    public void deleteTrainingById(long id) {
        Optional<Training> trainingOptional = trainingRepository.findById(id);
        if (trainingOptional.isPresent()) {
            trainingRepository.deleteById(id);
        } else {
            throw new TrainingNotFoundException("Training not found with ID: " + id);
        }
    }

    /**
     * Retrieves the details of a training from the training masters table based on its ID.
     * This method attempts to find a training with the given ID in the training masters table.
     * If the training is found, its details are returned. If the training is not found,
     * a {@link TrainingNotFoundException} is thrown.
     *
     * @param id The ID of the training to be retrieved.
     * @return The details of the training with the specified ID.
     * @throws TrainingNotFoundException If a training with the specified ID is not found in the table.
     */
    @Override
    public Training getTrainingById(long id) {
        Optional<Training> trainingOptional = trainingRepository.findById(id);
        if (trainingOptional.isPresent()) {
            return trainingOptional.get();
        } else {
            throw new TrainingNotFoundException("Training not found with ID: " + id);
        }
    }

    /**
     * Updates the details of a training in the training masters table based on its ID.
     * This method attempts to find a training with the given ID in the training masters table.
     * If the training is found, its details are updated with the provided {@code trainingMaster}.
     * If the training is not found, a {@link TrainingNotFoundException} is thrown.
     *
     * @param id         The ID of the training to be updated.
     * @param training The updated details for the training.
     * @return The updated training if it exists.
     * @throws TrainingNotFoundException If a training with the specified ID is not found in the table.
     */
    @Override
    public Training updateTraining(long id, Training training) {

        Optional<Training> trainingDetails = trainingRepository.findById(id);
        if (trainingDetails.isPresent()) {
            Training existingTraining = trainingDetails.get();
            existingTraining.setName(training.getName());
            return trainingRepository.save(existingTraining);
        } else {
            throw new TrainingNotFoundException("Training not found with id:" + id);
        }
    }

    /**
     * Validates a Training object by checking if a training with the same name already exists in the repository.
     *
     * <p>This method is useful for ensuring that the name of a new or updated training is unique
     * within the repository to maintain data integrity.
     *
     * @param training The Training object to be validated.
     * @return The original Training object if validation is successful; otherwise, the existing Training
     *         object with the same name found in the repository.
     */
    @Override
    public Training validateTraining(Training training) {
        return trainingRepository.findByName(training.getName());
    }
}
