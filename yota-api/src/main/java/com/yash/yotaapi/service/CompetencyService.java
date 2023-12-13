package com.yash.yotaapi.service;

import java.util.List;

import com.yash.yotaapi.domain.Competency;

/**
 * Service interface for managing competency in the system.
 * Defines methods for adding, retrieving, updating, and deleting competency details.
 *
 * @author gaurav.patil
 
 */
public interface CompetencyService {
	
	 /**
     * Adds a new competency to the system.
     * 
     * This method is responsible for adding a new competency with the provided details to the system.
     *
     * @param compentencyMaster The competency details to be added.
     * @return The added competency.
     */
    Competency addCompetency(Competency compentency);

    /**
     * This method is responsible for fetching a list of all competency details from the table.
     *
     * @return A List containing all competencies in the system
     */
    List<Competency> getAllCompetency();
    /**
     * This method is responsible for deleting a competency with the specified ID from the system.
     *
     * @param id The ID of the competency to be deleted.
     */
    void deleteCompetencyById(long id);

    /**
     * This method fetch and returns the details of a competency with the specified ID from the system.
     *
     * @param id The ID of the competency to be retrieved.
     * @return The details of the competency with the specified ID.
     */
    Competency getCompetencyById(long id);

    /**
     * This method used to find a competnecy with the given ID in the system.
     *
     * @param id         The ID of the competency to be updated.
     * 
     * 
     * @param competencMaster The updated details for the competency.
     * @return The updated competency.
     */
    Competency updateCompetency(long id, Competency competencyMaster);
    
    boolean getCompetencyByName(String competencyName);

}
