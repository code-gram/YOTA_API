package com.yash.yotaapi.services.impls;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.dto.TechnologyDto;
import com.yash.yotaapi.entity.Technology;
import com.yash.yotaapi.exceptions.TechnologyAlreadyAvailableException;
import com.yash.yotaapi.repositories.TechnologyRepository;
import com.yash.yotaapi.services.IServices.ITechnologyService;

import io.jsonwebtoken.lang.Assert;

@Service
public class TechnologyServiceImpl  implements ITechnologyService{

	@Autowired
	private TechnologyRepository technologyRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public TechnologyDto addTechnology(String technology) {
		 // Check if the technology already exists
        Technology existingTechnology = technologyRepository.findByTechnology(technology);
        if (existingTechnology!=null) {
            // If technology already exists, return null or throw an exception
            // In this example, I'm returning null, but you can handle it as needed
            throw new TechnologyAlreadyAvailableException("Technology is already available");
        }
        Technology technology2=Technology.builder().technology(technology).build();
        
        // Save the technology
        technology2=technologyRepository.save(technology2);
		Assert.notNull(technology2);
		return this.mapper.map(technology2, TechnologyDto.class);
       
    }

	@Override
	public List<TechnologyDto> fetchAllTechnology() {
		// TODO Auto-generated method stub
		List<Technology> technologies= technologyRepository.findAll();
		return technologies.stream().map(t->this.mapper.map(t, TechnologyDto.class)).collect(Collectors.toList());
		
	}
	}


