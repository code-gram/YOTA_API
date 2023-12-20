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
 * This class provides business logic related to the management of cometency in
 * the system.
 * 
 * @author gaurav.patil
 */
@Service
public class CompetencyServiceImpl implements CompetencyService {

	@Autowired
	private CompetencyRepository competencyRepo;

	
	@Override
	@Transactional
	public Competency addCompetency(Competency compentency) {

		return competencyRepo.save(compentency);

	}


	@Override
	public List<Competency> getAllCompetency() {
		return competencyRepo.findAll();

	}

	
	@Override
	public void deleteCompetencyById(Long id) {
		Optional<Competency> competencyOptional = competencyRepo.findById(id);
		if (competencyOptional.isPresent()) {
			competencyRepo.deleteById(id);
		} else {
			throw new CompetencyNotFoundException("Competency Not Found!!!...");
		}

	}

	@Override
	public Competency getCompetencyById(Long id) {
		Optional<Competency> competencyOptional = competencyRepo.findById(id);
		if (competencyOptional.isPresent()) {
			return competencyOptional.get();
		} else {
			throw new CompetencyNotFoundException("Competency Not Found!!!...");
		}

	}

	@Override
	public Competency updateCompetency(Long id, Competency competency) {
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

	public Boolean getCompetencyByName(String competencyName) {
		Competency competency = competencyRepo.findByName(competencyName);

		return competency != null;

	}

}
