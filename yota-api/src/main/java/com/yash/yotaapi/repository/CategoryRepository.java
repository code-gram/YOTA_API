package com.yash.yotaapi.repository;

import com.yash.yotaapi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query("SELECT c FROM Category c WHERE c.technologyMaster.id = :techId")
    List<Category> findCategoryNamesByTechnologyId(@Param("techId") Long technologyId);
}