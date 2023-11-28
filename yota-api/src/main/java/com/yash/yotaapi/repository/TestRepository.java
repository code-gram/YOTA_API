package com.yash.yotaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yash.yotaapi.domain.Test;

public interface TestRepository extends JpaRepository<Test, Long> {

	/**
	 * findByName method will give the Test details by name.
	 * 
	 * @param name of the test to be searched
	 * @return Test object if found otherwise null
	 */

	/*
	 * @Query(value = "SELECT * from test t WHERE t.test_id = :id", nativeQuery =
	 * true) Test findTestById(@Param("id") long id);
	 * 
	 *//**
		 * 
		 * deleteByTestName method will delete the Test by name
		 * 
		 * @param Name of the Test to be deleted
		 * @return true if Test is deleted, otherwise should throw exception
		 *//*
			 * @Modifying
			 * 
			 * @Query(value = "DELETE from test t WHERE t.test_id = :id" , nativeQuery =
			 * true) boolean deleteTestById(@Param("id") long id);
			 */

}
