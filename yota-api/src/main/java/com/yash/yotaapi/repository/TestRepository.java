package com.yash.yotaapi.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.yash.yotaapi.domain.ParentTechnology;
import com.yash.yotaapi.domain.Test;



import com.yash.yotaapi.domain.Test;
import java.util.List;


/**
 * TestRepository will perform all the CRUD Operations on Test Bank. 
 * In case if any customization is required on CRUD operations, it can be done here. 

 * Always extend JPARepository<Type,SerialVersionId>

 * @author jaie.arkadi
 */
public interface TestRepository extends JpaRepository<Test, Long> {


	/**

	 *  findByTestId method will give the Test details by Id.
	 * @param id of the Test to be searched

	 * @return Test object if found otherwise null
	 */
	Test findTestByTestId(Long TestId);
	
	/**

	 * deleteByTestId method will delete the Test by Id
	 * @param id of the Test to be deleted

	 * @return true if Test is deleted, otherwise should throw exception
	 */
	boolean deleteTestByTestId(Long TestId);


	/**

	 * findByTechnology method will search tests the technologyId
	 * @param id of technology to be searched

	 * @return set of tests according to technologyId
	 */
 
 	Set<Test> findByTechnology(ParentTechnology technology);
}
