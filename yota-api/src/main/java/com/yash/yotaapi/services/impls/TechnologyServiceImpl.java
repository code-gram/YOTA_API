package com.yash.yotaapi.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.entity.Technology;
import com.yash.yotaapi.exceptions.TechnologyAlreadyAvailableException;
import com.yash.yotaapi.repositories.TechnologyRepository;
import com.yash.yotaapi.services.IServices.ITechnologyService;

@Service
public class TechnologyServiceImpl  implements ITechnologyService{

	@Autowired
	private TechnologyRepository technologyRepository;
	
	@Override
	public Technology addTechnology(String technology) {
		 // Check if the technology already exists
        Technology existingTechnology = technologyRepository.findByTechnology(technology);
        if (existingTechnology!=null) {
            // If technology already exists, return null or throw an exception
            // In this example, I'm returning null, but you can handle it as needed
            throw new TechnologyAlreadyAvailableException("Technology is already available");
        }
        Technology technology2=new Technology();
        // Initialize countQuestion 
        technology2.setCountQuestion(0);
        technology2.setTechnology(technology);
        
        // Save the technology
        return technologyRepository.save(technology2);
    }
	}


