package com.yash.yotaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.yotaapi.domain.Test;

/**
* TestRepository will perform all the CRUD Operations on Test. 
* In case if any customization is required on CRUD operations, it can be done here. 
* @author gaurav.patil
*/
@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
	
	/**
	 * findByName will give the test details based on given test name.
	 * @param testName
	 */
	Test findByName(String testName); 
}

