package com.yash.yotaapi.controller;

import com.yash.yotaapi.domain.Category;
import com.yash.yotaapi.repository.CategoryRepository;
import com.yash.yotaapi.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Category Controller will facilitates CRUD functionalities
*
* @author amar.sawant
*/

@Tag(name = "Category Controller", description = "Controller for Category")
@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@PostMapping("/create")
	public ResponseEntity<String> createCategory(@RequestBody Category category) {
		try {
			String result = categoryService.createNewCategory(category);
			return ResponseEntity.ok(result);
		}catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/getById/{technologyId}")
	public ResponseEntity<List<Category>> getCategoriesByTechnologyId(@PathVariable Long technologyId){
		 List<Category>categories=categoryService.getCategoriesByTechnologyId(technologyId);
		return ResponseEntity.ok(categories);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Category>> getAllCategories(){
		 List<Category>categories=categoryRepository.findAll();
		return ResponseEntity.ok(categories);
	}
	
	@DeleteMapping("/deleteCategory/{id}")
	public String deleteCategory(@PathVariable Long id) {
		return categoryService.delete(id);
	}
}
