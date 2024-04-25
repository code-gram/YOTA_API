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

    CategoryDto createCategory(CategoryDto categoryDto, Long techId);

    /**
     * finds the Category only when the parameters satisfied
     *
     * @param technologyId Long technology id
     * @param categoryId   Long category id
     * @return CategoryDto object
     * @author yashr
     * @since 24-04-24
     */
    CategoryDto findCategoryByTechnologyIdAndCategoryId(Long technologyId, Long categoryId);

    List<CategoryDto> getAllCategoriesUnderTechnologyById(Long technologyId);

}
