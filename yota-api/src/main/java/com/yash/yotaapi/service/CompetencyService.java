package com.yash.yotaapi.service;

import java.util.List;

import com.yash.yotaapi.domain.Competency;
import com.yash.yotaapi.exception.CompetencyAlreadyExistsException;
import com.yash.yotaapi.exception.CompetencyNotFoundException;

/**
 * Service interface for managing competency in the system. Defines methods for
 * adding, retrieving, updating, and deleting competency details.
 *
 * @author gaurav.patil
 * 
 */
public interface CompetencyService {

	/**
	 * Adds a new competency to the competency_masters table.
	 *
	 * @param competency The competency to be added to the competency master table.
	 * @return The added competency if it does not already exists.
	 * @throws CompetencyAlreadyExistsException If the competency with the same Name
	 *                                          already exists in the table.
	 */
	Competency addCompetency(Competency compentency);

	/**
	 * This method retrieves a list of all competencies stored in the competency
	 * master table.
	 *
	 * @return A list containing all competencies from the competnecy master table.
	 */
	List<Competency> getAllCompetency();

	/**
	 * Deletes a competency from the competency_masters table based on its ID. This
	 * method attempts to find a competency with the given ID in the
	 * competency_masters table. If the competency is found, it is deleted. If the
	 * competency is not found, a {@link CompetencyNotFoundException} is thrown.
	 *
	 * @param id The ID of the competency to be deleted.
	 * @throws CompetencyNotFoundException If a competency with the specified ID is
	 *                                     not found in the table.
	 */
	void deleteCompetencyById(Long id);

	/**
	 * Retrieves the details of a competency from the competency_masters table based
	 * on its ID. This method attempts to find a unit with the given ID in the
	 * competency_masters table. If the competency is found, its details are
	 * returned. If the competency is not found, a
	 * {@link CompetencyNotFoundException} is thrown.
	 *
	 * @param id The ID of the competency to be retrieved.
	 * @return The details of the competency with the specified ID.
	 * @throws CompetencyNotFoundException If a competency with the specified ID is
	 *                                     not found in the table.
	 */
	Competency getCompetencyById(Long id);

	/**
	 * Updates the details of a competency in the competency_masters table based on
	 * its ID. This method attempts to find a unit with the given ID in the
	 * competency_masters table. If the competency is found, its details are updated
	 * with the provided {@code competency}. If the competency is not found, a
	 * {@link CompetencyNotFoundException} is thrown.
	 *
	 * @param id         The ID of the competency to be updated.
	 * @param competency The updated details for the competency.
	 * @return The updated competency if it exists.
	 * @throws CompetencyNotFoundException If a competency with the specified ID is
	 *                                     not found in the table.
	 */
	Competency updateCompetency(Long id, Competency competencyMaster);

	/**
	 * Retrieves the details of a competency from the competency_masters table based
	 * on competencyName.
	 * 
	 * @param competencyName
	 * @return True if the Competency Name exists.
	 */
	Boolean getCompetencyByName(String competencyName);

}
