package com.yash.yotaapi.service;

import com.yash.yotaapi.domain.Category;

import java.util.List;


public interface CategoryService {
	
	String createNewCategory(Category category);
	
	List<Category>getCategoriesByTechnologyId(Long technologyId);

	String delete(Long id);

}
