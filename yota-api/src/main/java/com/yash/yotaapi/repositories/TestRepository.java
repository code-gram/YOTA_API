package com.yash.yotaapi.repositories;


import com.yash.yotaapi.entity.Tests;
import com.yash.yotaapi.entity.Trainings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yash.yotaapi.entity.Test;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Tests, Long>  {
	Tests findByTestTitle(String test);

	@Query(value = "select tests_test_id from testes_assign tests_test_id where tests_test_id.assign_email_add=?1", nativeQuery = true)
	List<Long> getTestIdByEmailId(@Param("assign_email_add") String assign_email_add);

	@Query(value = "SELECT COUNT(test_id) FROM testes WHERE test_id = :testId AND result_result_id IS NOT NULL", nativeQuery = true)
	Long countTestsByTestIdAndNotEmptyResultId(@Param("testId") Long testId);

//	@Query(value = "select * from testes where test_id= :testId", nativeQuery = true)
//	Tests getTestsById(@Param("testId") Long testId);

	@Query(value = "SELECT test_id, test_title, start_date, end_date, action FROM testes WHERE test_id = :testId", nativeQuery = true)
	List<Object[]> getTestsById(@Param("testId") Long testId);



}
