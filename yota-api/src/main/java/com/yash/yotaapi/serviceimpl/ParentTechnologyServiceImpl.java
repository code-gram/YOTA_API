package com.yash.yotaapi.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.ParentTechnology;
import com.yash.yotaapi.exception.ParentTechnologyException;
import com.yash.yotaapi.exception.ParentTechnologyNotFoundException;
import com.yash.yotaapi.repository.ParentTechnologyRepository;
import com.yash.yotaapi.service.ParentTechnologyService;

import javax.transaction.Transactional;


/**This is service layer class for Parent Technology to write business logic
 * @author pratik.kurbet
 *
 */
@Service
public class ParentTechnologyServiceImpl implements ParentTechnologyService{
	
	/**
	 * ParentTechnologyRepository is used to interact service layer with repository layer.
	 */
	@Autowired
	private ParentTechnologyRepository parentTechnologyRepository;


	

	/**
	 * This method is for save ParentTechnology to DB through repository layer
	 */

	@Override
	public ParentTechnology save(ParentTechnology technology) {
		try {
			technology.setName(technology.getName().toUpperCase());
			return parentTechnologyRepository.save(technology);
		} catch (Exception e) {
			throw new ParentTechnologyException("Technology : "+technology.getName().toUpperCase()+" already exists!!");
		}
	}

	/**
	 * This method is for get all ParentTechnologies from DB through repository layer
	 */
	@Override
	public List<ParentTechnology> getAllTechs() {
		return parentTechnologyRepository.findAll();
	}

	/**
	 * This method is for delete ParentTechnology from DB through repository layer
	 */
	@Override
	@Transactional
	public void removeTech(long id) {
		ParentTechnology parentTechnology=parentTechnologyRepository.findById(id).get();
		if (parentTechnology==null) {
			throw new ParentTechnologyException("Technology with ID :"+id+" does not exist");
		}
		parentTechnologyRepository.deleteById(id);
		
	}
	
	/**
	 * This method is for update ParentTechnology to DB through repository layer
	 */
	@Override
	@Transactional
	public ParentTechnology updateTech(ParentTechnology technology) {
		ParentTechnology existParentTechnology=parentTechnologyRepository.getByName(technology.getName());
		if (existParentTechnology==null) {
			return parentTechnologyRepository.save(technology);
		} else {
			existParentTechnology.setName(technology.getName());
			existParentTechnology.setShortDescription(technology.getShortDescription());
			existParentTechnology.setStatus(technology.isStatus());
			parentTechnologyRepository.save(existParentTechnology);
		}
		return technology;
	}

	/**
	 * This method is for get ParentTechnology from DB through repository layer on basis of name only.
	 */
	@Override
	public ParentTechnology getTech(String name) {
		ParentTechnology technology=parentTechnologyRepository.getByName(name);
		if (technology==null) {
			throw new ParentTechnologyNotFoundException("Technology with name : "+name+" does not exist");
		}
		return technology;
	}
	
	/**
	 * This method is for get ParentTechnology from DB through repository layer on basis of keyword only.
	 */
	@Override
	public List<ParentTechnology> searchTech(String keyword) {
		List<ParentTechnology> list=parentTechnologyRepository.getByNameContaining(keyword.toUpperCase());
		if (list.isEmpty()) {
			throw new ParentTechnologyNotFoundException("Technology containing keyword  : "+keyword+" does not exist");
		}
		return parentTechnologyRepository.getByNameContaining(keyword.toUpperCase());
	}

}
