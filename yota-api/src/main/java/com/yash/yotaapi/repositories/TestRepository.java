package com.yash.yotaapi.repositories;


import com.yash.yotaapi.entity.Tests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Tests, Long>  {
	Tests findByTestTitle(String test);

	@Query(value = "select tests_test_id from tests_assign tests_test_id where tests_test_id.assign_email_add=?1", nativeQuery = true)
	List<Long> getTestIdByEmailId(@Param("assign_email_add") String assign_email_add);

	@Query(value = "SELECT COUNT(test_id) FROM tests WHERE test_id = :testId AND result_result_id IS NOT NULL", nativeQuery = true)
	Long countTestsByTestIdAndNotEmptyResultId(@Param("testId") Long testId);

	@Query(value = "SELECT test_id, test_title, start_date, end_date, action FROM tests WHERE test_id = :testId", nativeQuery = true)
	List<Object[]> getTestsById(@Param("testId") Long testId);

	@Query(value = "SELECT t.* FROM tests t " +
			"JOIN tests_assign ta ON t.test_id = ta.tests_test_id " +
			"JOIN yota_user u ON ta.assign_email_add = u.email_add " +
			"WHERE t.test_id = :testId AND u.email_add = :email", nativeQuery = true)
	Optional<Tests> findByTestIdAndUserEmail(@Param("testId") Long testId, @Param("email") String email);

	@Query(value = "select questions_ques_id from tests_questions questions_ques_id where questions_ques_id.tests_test_id=?1", nativeQuery = true)
	List<Long> getQuestionIdByTestId(@Param("tests_test_id") Long tests_test_id);

	@Modifying
	@Transactional
	@Query(value = "insert into tests_questions (tests_test_id, questions_ques_id) values (:testId, :questionIds)", nativeQuery = true)
	Integer addQuestionsInTest(@Param("testId") Long testId,
			                   @Param("questionIds") Long questionIds);

	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query("update Tests t set t.totalQuestions=?1 where t.id=?2")
	Integer updateTotalQuestionCount(Integer totalQuestions, Long id);

	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query("update Tests t set t.totalAssociateCount=?1 where t.id=?2")
    Integer updateTotalAssociateCount(Integer totalAssociateCount, Long testId);
}

