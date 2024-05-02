package com.yash.yotaapi.services.impls;

import com.yash.yotaapi.dto.CategoryDto;
import com.yash.yotaapi.dto.TechnologyDto;
import com.yash.yotaapi.entity.Technology;
import com.yash.yotaapi.exceptions.ApplicationException;
import com.yash.yotaapi.exceptions.TechnologyAlreadyAvailableException;
import com.yash.yotaapi.repositories.TechnologyRepository;
import com.yash.yotaapi.services.IServices.ITechnologyService;
import io.jsonwebtoken.lang.Assert;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
}


