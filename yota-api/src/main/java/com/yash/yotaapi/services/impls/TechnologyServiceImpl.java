package com.yash.yotaapi.services.impls;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.dto.CategoryDto;
import com.yash.yotaapi.dto.TechnologyDto;
import com.yash.yotaapi.entity.Technology;
import com.yash.yotaapi.exceptions.ApplicationException;
import com.yash.yotaapi.exceptions.TechnologyAlreadyAvailableException;
import com.yash.yotaapi.repositories.TechnologyRepository;
import com.yash.yotaapi.services.IServices.ITechnologyService;

@Service
public class TechnologyServiceImpl implements ITechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public TechnologyDto addTechnology(TechnologyDto technology) {
        // Check if the technology already exists
        Technology existingTechnology = technologyRepository.findByTechnology(technology.getTechnology());
        if (existingTechnology != null) {
            throw new TechnologyAlreadyAvailableException("Technology is already available");
        }
        Technology technology2 = this.mapper.map(technology, Technology.class);
        technology2 = technologyRepository.save(technology2);
        return this.mapper.map(technology2, TechnologyDto.class);

    }

    @Override
    public TechnologyDto findTechnologyById(Long techId) {
        if (ObjectUtils.isNotEmpty(techId)) {
            Technology technology = this
                    .technologyRepository
                    .findTechnologyById(techId)
                    .orElseThrow(() -> new ApplicationException("Technology not found..."));

            return this
                    .mapper
                    .map(technology, TechnologyDto.class);
        } else
            throw new ApplicationException("Technology id is empty or null, please check and try again !!");
    }

    @Override
    public List<TechnologyDto> fetchAllTechnology() {
        List<Technology> technologies = technologyRepository.findAll();
        List<TechnologyDto> technologyDtoList = technologies
                .stream()
                .map(t -> this
                        .mapper
                        .map(t, TechnologyDto.class))
                .collect(Collectors.toList());

        technologyDtoList
                .forEach(tech -> {
                    List<CategoryDto> categories = tech.getCategories();

                    if (!categories.isEmpty()) {
                        categories
                                .forEach(cat -> {
                                    Integer countUnderCategory = cat.getQuestions().size();
                                    cat.setQuestionCountUnderCategory(countUnderCategory);
                                });

                        Integer countUnderTechnology = categories
                                .stream()
                                .mapToInt(CategoryDto :: getQuestionCountUnderCategory)
                                .sum();
                        tech.setQuestionCountUnderTechnology(countUnderTechnology);
                    }
                });
        return technologyDtoList;
    }

    @Override
    public TechnologyDto updateTechnology(Long techId, TechnologyDto technology) {
        Optional<Technology> existingTechnologyOptional = technologyRepository.findById(techId);
        if (!existingTechnologyOptional.isPresent()) {
            throw new ApplicationException("Technology not found for update with id: " + techId);
        }

        if (!technology.getTechnology().equals(existingTechnologyOptional.get().getTechnology())) {
            Technology existingByName = technologyRepository.findByTechnology(technology.getTechnology());
            if (existingByName != null) {
                throw new TechnologyAlreadyAvailableException("Technology name already exists: " + technology.getTechnology());
            }
        }

        Technology existingTechnology = existingTechnologyOptional.get();
        existingTechnology.setTechnology(technology.getTechnology()); // Update other fields as needed
        Technology updatedTechnology = technologyRepository.save(existingTechnology);

        return mapper.map(updatedTechnology, TechnologyDto.class);
    }


}