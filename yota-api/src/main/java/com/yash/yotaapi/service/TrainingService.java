package com.yash.yotaapi.service;
 
import java.util.Date;
import java.util.List;
import com.yash.yotaapi.domain.Training;
 
/**
* TrainingService is a service layer providing business logic for Training entities.
* It declares methods to perform CRUD operations and search functionalities for Training entities.
*
* @author raghav.muchhal
*/
public interface TrainingService {
 
    /**
     * Create a new training entity.
     *
     * @param training The training object to be created.
     * @return The created training object.
     */
    Training createTraining(Training training);
 
    /**
     * Retrieve a training entity by its ID.
     *
     * @param trainingId The ID of the training to retrieve.
     * @return The training entity with the given ID.
     */
    Training getTraining(long trainingId);
 
    /**
     * Update details of a training entity.
     *
     * @param training The updated training object.
     * @param trainingId  The ID of the training to update.
     * @return The updated training object.
     */
    Training updateTrainingDetails(Training training, long trainingId);
 
    /**
     * Remove a training entity by its ID.
     *
     * @param trainingId The ID of the training to delete.
     */
    void removeTrainingDetails(long trainingId);
 
    /**
     * Get all training entities.
     *
     * @return List of all training entities.
     */
    List<Training> getAllDetails();
 
    /**
     * Search for training entities by a keyword.
     *
     * @param keyword The keyword to search for in training details.
     * @return List of training entities matching the keyword.
     */
    List<Training> searchTraining(String keyword);
 
    /**
     * Get training entities within a specified start and end date range.
     *
     * @param startDate The start date of the range.
     * @param endDate   The end date of the range.
     * @return List of training entities within the specified date range.
     */
    
   
    List<Training> getByStartDateAndEndDate(Date startDate, Date endDate);


	public void updateStatusOnTrainingReject(Long trainingId,String action,String rejectMessage);
	
    /**
     * @author pragati.paliwal
     * @param fetching startDate
     * @param fetching endDate
     * @return changing training status to approve.
     */
    public Training updateActualStartAndEndDate(Training training, long trainingId);

}