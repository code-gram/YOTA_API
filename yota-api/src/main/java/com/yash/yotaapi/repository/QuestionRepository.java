package com.yash.yotaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.yotaapi.model.Question;



/**
 * QuestionRepository will perform all the CRUD Operations on Question Bank. 
 * In case if any customization is required on CRUD operations, it can be done here. 
 * Always extend JPARepository<Type,SerialVersionId>
 * @author pankaj.sharma
 * @author priya.m
 *
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	// TODO: no need to add crud operations over here, if any customization is required, we can customize the methods.

	/**
	 *  findByQuestionType method will give the Question details by Type.
	 * @param type of the Question to be searched
	 * @return Question object if found otherwise null
	 */
	Question findQuestionById(Long questionId);
	
	/**
	 * deleteByQuestionType method will delete the Question by type
	 * @param type of the question to be deleted
	 * @return true if Question is deleted, otherwise should throw exception
	 */
 boolean deleteQuestionById(Long questionId);

	
	//Iterable<Question> findAll();
}
