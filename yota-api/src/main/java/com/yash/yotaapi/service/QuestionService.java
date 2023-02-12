package com.yash.yotaapi.service;

import com.yash.yotaapi.domain.Question;

/**
 * QuestionService will be performing business logic related to Question Bank.
 * @author pankaj.ssharma
 * @author priya.m
 *
 */
public interface QuestionService {
	
	/**
	 * save method will save the Questions. If same question is already available then it should throw relevant exception.
	 * @param question to be saved
	 * @return Saved question, it should hold the id of the new question saved in DB
	 *
	 */
	//Question save(Question question);

	public Question saveOrUpdate(Question question);

	/**
	 * findByQuestionType method will fetch question based on its type from DB
	 * @param questionId
	 * @return questions according to questions type
	 */
	public Question findQuestionById(Long questionId);
	
	/**
	 * findAllQuestion method will fetch all Questions from DB
	 * @return List of Questions
	 */
	public Iterable<Question> findAllQuestion();
	
	/**
	 * deleteQuestionByQuestionType method will delete the question that is mentioned
	 * @param questionId
	 *  if deleted, otherwise false, in case question is not available then it should throw appropriate exception.
	 */
	
	public void deleteQuestionById(Long questionId);
}
