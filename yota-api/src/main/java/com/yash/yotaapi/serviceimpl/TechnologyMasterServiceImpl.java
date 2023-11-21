package com.yash.yotaapi.serviceimpl;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.Client;
import com.yash.yotaapi.domain.TechnologyMaster;
import com.yash.yotaapi.exception.ParentTechnologyException;
import com.yash.yotaapi.exception.ParentTechnologyNotFoundException;
import com.yash.yotaapi.repository.TechnologyMasterRepository;
import com.yash.yotaapi.service.TechnologyMasterService;

/**
 * This is service layer class for Parent Technology to write business logic
 * 
 * @author pratik.kurbet
 *
 */
@Service

public class TechnologyMasterServiceImpl implements TechnologyMasterService {

	/**
	 * ParentTechnologyRepository is used to interact service layer with repository
	 * layer.
	 */
	@Autowired
	private TechnologyMasterRepository technologyMasterRepository;

	/**
	 * This method is for save ParentTechnology to DB through repository layer
	 */
	@Override
	public TechnologyMaster save(TechnologyMaster technology) {
		try {
			technology.setId(technology.getId());
			return technologyMasterRepository.save(technology);
		} catch (DataIntegrityViolationException e) {
			throw new ParentTechnologyException("Technology with Name  " + technology.getId() + " already exists!!");
		}
	}

	/**
	 * This method is for get all ParentTechnologies from DB through repository
	 * layer
	 */
	@Override
	public List<TechnologyMaster> getAllTechs() {
		return technologyMasterRepository.findAll();
	}

	/**
	 * This method is for delete ParentTechnology from DB through repository layer
	 */
	@Override
	@Transactional
	public void removeTech(long id) {
		try {
			technologyMasterRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ParentTechnologyException("Technology with ID :" + id + " does not exist");
		}
		technologyMasterRepository.deleteById(id);

	}

	/**
	 * This method is for update ParentTechnology to DB through repository layer
	 */
	@Override
	@Transactional
	public TechnologyMaster updateTech(TechnologyMaster technology) {
		TechnologyMaster existParentTechnology = technologyMasterRepository.getByName(technology.getName());
		if (existParentTechnology == null) {
			return technologyMasterRepository.save(technology);
		} else {
			existParentTechnology.setName(technology.getName());
			existParentTechnology.setShortDescription(technology.getShortDescription());
			existParentTechnology.setStatus(technology.isStatus());
			technologyMasterRepository.save(existParentTechnology);
		}
		return technology;
	}

	@Override
	public TechnologyMaster updateTech(TechnologyMaster technology, long id) {

		TechnologyMaster technologyDetails = technologyMasterRepository.findById(id).get();
		if (technologyDetails == null) {
			throw new ParentTechnologyNotFoundException(
					"TechnologyMaster id: " + id + " is not present in TechnologyDetails ");
		} else {
			technologyDetails.setName(technology.getName());
			technologyDetails.setShortDescription(technology.getShortDescription());

			TechnologyMaster updateTech = technologyMasterRepository.save(technologyDetails);
			return updateTech;
		}
	}

	/**
	 * This method is for get ParentTechnology from DB through repository layer on
	 * basis of name only.
	 */
	@Override
	public TechnologyMaster getTech(long id) {
		TechnologyMaster technology = technologyMasterRepository.findById(id).get();
		if (technology == null) {
			throw new ParentTechnologyNotFoundException("Technology with name : " + id + " does not exist");
		}
		return technology;
	}

	/**
	 * This method is for get ParentTechnology from DB through repository layer on
	 * basis of keyword only.
	 */
	@Override
	public List<TechnologyMaster> searchTech(String keyword) {
		List<TechnologyMaster> list = technologyMasterRepository.getByNameContaining(keyword.toUpperCase());
		if (list.isEmpty()) {
			throw new ParentTechnologyNotFoundException(
					"Technology containing keyword  : " + keyword + " does not exist");
		}
		return technologyMasterRepository.getByNameContaining(keyword.toUpperCase());
	}

}
