package com.yash.yotaapi.services.IServices;

import com.yash.yotaapi.dto.CategoryDto;

import java.util.List;

/**
 * Project Name - YOTA_NEW
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yashr
 * @since - 23-04-2024
 */
public interface ICategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategoriesUnderTechnologyById(Long technologyId);

    Long getQuestionCountUnderCategory(Long categoryId);
}
