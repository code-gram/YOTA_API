package com.yash.yotaapi.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.Competency;
import com.yash.yotaapi.exception.CompetencyAlreadyExistsException;
import com.yash.yotaapi.exception.CompetencyNotFoundException;
import com.yash.yotaapi.repository.CompetencyRepository;
import com.yash.yotaapi.service.CompetencyService;

/**
 * Implementation of the {@link CometencyService} interface.
 * 
 * This class provides business logic related to the management of cometency in the
 * system.
 * 
 * @author gaurav.patil
 */
@Service
public class CompetencyServiceImpl implements CompetencyService {

	@Autowired
	CompetencyRepository competencyRepo;
	
	/**
     * Adds a new competency to the competency_masters table.
     *
     * @param competency The competency to be added to the competency master table.
     * @return The added competency if it does not already exists.
     * @throws CompetencyAlreadyExistsException If the competency with the same Name already exists in the table.
     */
	@Override
	@Transactional
	public Competency addCompetency(Competency compentency) {

		return competencyRepo.save(compentency);

	}
	
	 /**
     * This method retrieves a list of all competencies stored in the competency master table.
     *
     * @return A list containing all competencies from the competnecy master table.
     */

	@Override
	public List<Competency> getAllCompetency() {
		return competencyRepo.findAll();

	}
	
	/**
     * Deletes a competency from the competency_masters table based on its ID.
     * This method attempts to find a competency with the given ID in the competency_masters table.
     * If the competency is found, it is deleted. If the competency is not found, a {@link CompetencyNotFoundException}
     * is thrown.
     *
     * @param id The ID of the competency to be deleted.
     * @throws CompetencyNotFoundException If a competency with the specified ID is not found in the table.
     */
	@Override
	public void deleteCompetencyById(long id) {
		Optional<Competency> competencyOptional = competencyRepo.findById(id);
		if (competencyOptional.isPresent()) {
			competencyRepo.deleteById(id);
		} else {
			throw new CompetencyNotFoundException("Competency Not Found!!!...");
		}

	}
	
	/**
     * Retrieves the details of a competency from the competency_masters table based on its ID.
     * This method attempts to find a unit with the given ID in the competency_masters table.
     * If the competency is found, its details are returned. If the competency is not found,
     * a {@link CompetencyNotFoundException} is thrown.
     *
     * @param id The ID of the competency to be retrieved.
     * @return The details of the competency with the specified ID.
     * @throws CompetencyNotFoundException If a competency with the specified ID is not found in the table.
     */
	@Override
	public Competency getCompetencyById(long id) {
		Optional<Competency> competencyOptional = competencyRepo.findById(id);
		if (competencyOptional.isPresent()) {
			return competencyOptional.get();
		} else {
			throw new CompetencyNotFoundException("Competency Not Found!!!...");
		}

	}
	
	 /**
     * Updates the details of a competency in the competency_masters table based on its ID.
     * This method attempts to find a unit with the given ID in the competency_masters table.
     * If the competency is found, its details are updated with the provided {@code competency}.
     * If the competency is not found, a {@link CompetencyNotFoundException} is thrown.
     *
     * @param id The ID of the competency to be updated.
     * @param competency The updated details for the competency.
     * @return The updated competency if it exists.
     * @throws CompetencyNotFoundException If a competency with the specified ID is not found in the table.
     */
	@Override
	public Competency updateCompetency(long id, Competency competency) {
		Optional<Competency> CompetencyDetails = competencyRepo.findById(id);
		if (CompetencyDetails.isPresent()) {
			Competency existingCompetency = CompetencyDetails.get();
			if (!getCompetencyByName(competency.getName())) {
				existingCompetency.setName(competency.getName());
				existingCompetency.setCompetencyManager(competency.getCompetencyManager());
				return competencyRepo.save(existingCompetency);
			} else {
				throw new CompetencyAlreadyExistsException("Competency Already Exists...");
			}

		} else {
			throw new CompetencyNotFoundException("Competency Not Found with id:" + id);

		}
	}

	public boolean getCompetencyByName(String competencyName) {
		Competency competency = competencyRepo.findByName(competencyName);

		return competency != null;

	}

}
