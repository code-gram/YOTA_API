package com.yash.yotaapi.serviceimpl;

import com.yash.yotaapi.domain.Nomination;
import com.yash.yotaapi.repository.NominationRepository;
import com.yash.yotaapi.service.NominationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of NominationService providing business logic for Nomination
 * entities.
 * 
 * @author raghav.muchhal
 */
@Service
public class NominationServiceImpl implements NominationService {

	private final NominationRepository nominationRepository;

	@Autowired
	public NominationServiceImpl(NominationRepository nominationRepository) {
		this.nominationRepository = nominationRepository;
	}

	/**
	 * Creates a new nomination.
	 *
	 * @param nomination The nomination object to be created.
	 * @return The created nomination object.
	 */
	@Override
	public Nomination createNomination(Nomination nomination) {
		return nominationRepository.save(nomination);
	}

	/**
	 * Retrieves all nominations.
	 *
	 * @return List of all nominations.
	 */
	@Override
	public List<Nomination> getAllNominations() {
		return nominationRepository.findAll();
	}

	/**
	 * Retrieves a nomination by its ID.
	 *
	 * @param nominationId The ID of the nomination to retrieve.
	 * @return The nomination with the given ID.
	 */
	@Override
	public Nomination getNominationById(Long nominationId) {
		Optional<Nomination> optionalNomination = nominationRepository.findById(nominationId);
		return optionalNomination.orElse(null);
	}

	/**
	 * Updates a nomination.
	 *
	 * @param nominationId      The ID of the nomination to update.
	 * @param updatedNomination The updated nomination object.
	 * @return The updated nomination object.
	 */
	@Override
	public Nomination updateNomination(Long nominationId, Nomination updatedNomination) {
		if (nominationRepository.existsById(nominationId)) {
			updatedNomination.setNominationId(nominationId);
			return nominationRepository.save(updatedNomination);
		}
		return null;
	}

	/**
	 * Deletes a nomination by its ID.
	 *
	 * @param nominationId The ID of the nomination to delete.
	 */
	@Override
	public void deleteNomination(Long nominationId) {
		nominationRepository.deleteById(nominationId);
	}

	/**
	 * Finds nominations by employee name.
	 *
	 * @param employeeName The name of the employee.
	 * @return List of nominations associated with the given employee name.
	 */
	@Override
	public List<Nomination> findByEmployeeName(String employeeName) {
		return nominationRepository.findByEmployeeName(employeeName);
	}
}