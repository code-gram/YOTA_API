package com.yash.yotaapi.service;

import com.yash.yotaapi.domain.Question;

/**
 * QuestionService will be performing business logic related to Question Bank.
 * @author priya.m
 *
 */
public interface QuestionService {
	
	/**
	 * saveOrUpdate method will save the Questions.
	 * @param question to be saved
	 * @return Saved question, it should hold the id of the new question saved in DB
	 *
	 */

	public Question saveOrUpdate(Question question);

	/**
	 * findById method will fetch question based on its id from DB
	 * @param questionId
	 * @return questions according to questions id
	 */
	
	public Question findQuestionById(Long questionId);
	
	/**
	 * findAllQuestion method will fetch all Questions from DB
	 * @return List of Questions
	 */
	public Iterable<Question> findAllQuestion();
	
	/**
	 * deleteQuestionById method will delete the question that is mentioned
	 * @param questionId
	 * @return if deleted, otherwise false, in case question is not available then it should throw appropriate exception.
	 */
	
	public void deleteQuestionById(Long questionId);
	
	/**
	 * this updateQuestion method will update the question detail of the existing question
	 * @param question to be updated
	 * @return Updated Question
	 */
	public Question updateQuestion(Question question);
}
