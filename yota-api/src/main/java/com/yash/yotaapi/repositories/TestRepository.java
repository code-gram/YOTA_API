package com.yash.yotaapi.repositories;


import com.yash.yotaapi.entity.Tests;
import com.yash.yotaapi.entity.Trainings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Tests, Long>  {
	Tests findByTestTitle(String test);

	@Query(value = "select tests_test_id from testes_assign tests_test_id where tests_test_id.assign_email_add=?1", nativeQuery = true)
	List<Long> getTestIdByEmailId(@Param("assign_email_add") String assign_email_add);

	@Query(value = "SELECT COUNT(test_id) FROM testes WHERE test_id = :testId AND result_result_id IS NOT NULL", nativeQuery = true)
	Long countTestsByTestIdAndNotEmptyResultId(@Param("testId") Long testId);

	@Query(value = "SELECT test_id, test_title, start_date, end_date, action FROM testes WHERE test_id = :testId", nativeQuery = true)
	List<Object[]> getTestsById(@Param("testId") Long testId);

	@Query(value = "SELECT t.* FROM testes t " +
			"JOIN testes_assign ta ON t.test_id = ta.tests_test_id " +
			"JOIN yota_user u ON ta.assign_email_add = u.email_add " +
			"WHERE t.test_id = :testId AND u.email_add = :email", nativeQuery = true)
	Optional<Tests> findByTestIdAndUserEmail(@Param("testId") Long testId, @Param("email") String email);

}
