package com.yash.yotaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.yash.yotaapi.domain.Question;



import com.yash.yotaapi.domain.Question;

/**
 * QuestionRepository will perform all the CRUD Operations on Question Bank. 
 * In case if any customization is required on CRUD operations, it can be done here. 

 * Always extend JPARepository<Type,SerialVersionId>

 * @author priya.m
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {


	/**

	 *  findByQuestionId method will give the Question details by Id.
	 * @param id of the Question to be searched

	 * @return Question object if found otherwise null
	 */
	Question findQuestionById(Long questionId);
	
	/**

	 * deleteByQuestionId method will delete the Question by Id
	 * @param id of the question to be deleted

	 * @return true if Question is deleted, otherwise should throw exception
	 */
 boolean deleteQuestionById(Long questionId);

	
	//Iterable<Question> findAll();
}
