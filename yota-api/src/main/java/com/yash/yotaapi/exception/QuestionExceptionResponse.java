package com.yash.yotaapi.exception;

/**
 * 
 * @author priya.m
 *
 */
public class QuestionExceptionResponse {
	
	private String question;

	public QuestionExceptionResponse(String question) {
		super();
		this.question = question;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	
}
