package com.yash.yotaapi.controllers;

import com.yash.yotaapi.dto.CategoryDto;
import com.yash.yotaapi.services.IServices.ICategoryService;
import com.yash.yotaapi.validators.IsTechnicalManagerOrTrainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project Name - YOTA_NEW
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yashr
 * @since - 24-04-2024
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/create-new")
    @IsTechnicalManagerOrTrainer
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto,
                                                      @RequestParam Long techId) {
        CategoryDto category = this.categoryService.createCategory(categoryDto, techId);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("/get/{categoryId}")
    @IsTechnicalManagerOrTrainer
    public ResponseEntity<CategoryDto> findCategoryByTechnologyIdAndCategoryId(@RequestParam("tech_id") Long technologyId,
                                                                               @PathVariable Long categoryId) {
        CategoryDto category = this.categoryService.findCategoryByTechnologyIdAndCategoryId(technologyId, categoryId);
        return ResponseEntity.ok(category);
    }
}
