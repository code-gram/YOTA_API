package com.yash.yotaapi.repository;

/**
 * Repository layer will communicate with DB layer
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yash.yotaapi.domain.EmployeeResult;

/**
 * ParentTechnologyRepository will perform all the CRUD Operations on
 * ParentTechnology. In case if any customization is required on CRUD
 * operations, it can be done here and Always extend JPARepository with
 * Type,SerialVersionId
 * 
 * @author pankaj.ssharma
 *
 */
@Repository
public interface EmployeeResultRepository extends JpaRepository<EmployeeResult, Long> {

	/**
	 * findAll
	 * 
	 * @return List of student based on their order
	 */
	List<EmployeeResult> findByOrderByMarksAsc();

	List<EmployeeResult> findByOrderByMarksDesc();

}
