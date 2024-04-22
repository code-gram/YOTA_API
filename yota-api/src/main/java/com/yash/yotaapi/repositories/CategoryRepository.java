package com.yash.yotaapi.repositories;

import com.yash.yotaapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * this will return the unique categories under the technology
     *
     * @param technologyId Long technology id required to fetch the data
     * @return Unique set of categories
     * @author yashr
     * @since 22-04-24
     */
    @Query("SELECT CAT FROM Category CAT WHERE CAT.technology.id=?1")
    Set<Category> findAllCategoryByTechnologyId(Long technologyId);
}
