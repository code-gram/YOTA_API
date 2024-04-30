package com.yash.yotaapi.services.impls;

import com.yash.yotaapi.dto.CategoryDto;
import com.yash.yotaapi.dto.TechnologyDto;
import com.yash.yotaapi.entity.Category;
import com.yash.yotaapi.exceptions.ApplicationException;
import com.yash.yotaapi.repositories.CategoryRepository;
import com.yash.yotaapi.services.IServices.ICategoryService;
import com.yash.yotaapi.services.IServices.ITechnologyService;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Project Name - YOTA_NEW
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yashr
 * @since - 24-04-2024
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ITechnologyService technologyService;

    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public CategoryDto createCategory(CategoryDto categoryDto, Long techId) {
        Category category = null;
        if (ObjectUtils.isNotEmpty(categoryDto)) {
            TechnologyDto technologyDto = this
                    .technologyService
                    .findTechnologyById(techId);

            category = this
                    .categoryRepository
                    .findCategoryByNameAndTechnologyId(categoryDto.getName(), techId);

            if (ObjectUtils.isEmpty(category)) {
                categoryDto.setTechnology(technologyDto);

                category = this
                        .mapper
                        .map(categoryDto, Category.class);

                category = this
                        .categoryRepository
                        .save(category);

                categoryDto = this
                        .mapper
                        .map(category, CategoryDto.class);

                return categoryDto;
            } else
                throw new ApplicationException("Category already exists under this technology...");
        } else
            throw new ApplicationException("Category details are invalid or empty, please check and Try again...");
    }

    /**
     * finds the Category only when the parameters satisfied
     *
     * @param technologyId Long technology id
     * @param categoryId   Long category id
     * @return CategoryDto object
     * @author yashr
     * @since 24-04-24
     */
    @Override
    public CategoryDto findCategoryByTechnologyIdAndCategoryId(Long technologyId, Long categoryId) {
        Category category = null;
        if (ObjectUtils.isNotEmpty(technologyId)
                && ObjectUtils.isNotEmpty(categoryId)) {

            category = this
                    .categoryRepository
                    .findCategoryByTechnologyIdAndCategoryId(technologyId, categoryId)
                    .orElseThrow(() -> new ApplicationException("Category not found"));

            category.setQuestionCountUnderCategory(category.getQuestions().size());

            CategoryDto categoryDto = this
                    .mapper
                    .map(category, CategoryDto.class);

            category = null;
            return categoryDto;
        } else
            throw new ApplicationException("Missing Category id or Technology Id, please check and Try again...");
    }

    @Override
    public List<CategoryDto> getAllCategoriesUnderTechnologyById(Long technologyId) {
    	    	List<Category> categories = this.categoryRepository.findAllCategoryByTechnologyId(technologyId);
    	        List<CategoryDto> categoryDtos = new ArrayList<>();
    	        for (Category category : categories) {
    	            CategoryDto categoryDto = this.mapper.map(category, CategoryDto.class);
    	            categoryDtos.add(categoryDto);
    	        }

    	        return categoryDtos;
    	    
    	    	
    	    }
    }

