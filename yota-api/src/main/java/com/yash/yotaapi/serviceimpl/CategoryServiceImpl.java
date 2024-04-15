package com.yash.yotaapi.serviceimpl;

import com.yash.yotaapi.domain.Category;
import com.yash.yotaapi.domain.TechnologyMaster;
import com.yash.yotaapi.exception.NoDataFoundException;
import com.yash.yotaapi.repository.CategoryRepository;
import com.yash.yotaapi.repository.TechnologyMasterRepository;
import com.yash.yotaapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	TechnologyMasterRepository technologyMasterRepository;

	@Override
	public String createNewCategory(Category category) {
		
		Long techId=category.getTechnologyMaster().getId();
		Optional<TechnologyMaster> technologyOptional = technologyMasterRepository.findById(techId);
		if(technologyOptional.isPresent()) {
			TechnologyMaster technologyMaster=technologyOptional.get();
			Category category1=new Category();
			category1.setTechnologyMaster(technologyMaster);
			categoryRepository.save(category);
			return "Category Created Successfully";
		} else {
			throw new IllegalArgumentException("Technology not found");
		}
	}

	@Override
	public List<Category> getCategoriesByTechnologyId(Long technologyId) {
		// TODO Auto-generated method stub
		return categoryRepository.findCategoryNamesByTechnologyId(technologyId);
	}

	@Override
	public String delete(Long id) {
		try
		{
			categoryRepository.findById(id).get();
		}catch (NoSuchElementException e) {
			throw new NoDataFoundException("Category with Id:"+id+" does not exist.");
		}
		categoryRepository.deleteById(id);
		return "category deleted..";	
	}
}