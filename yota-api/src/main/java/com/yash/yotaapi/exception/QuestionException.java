package com.yash.yotaapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is an unchecked exception. This will be creating the QuestionException Object for two purpose. 
 * 1: To test question Exception at the time of TDD, this will be done by Default Constructor. 
 * 2: To create the customized Exception message, this will be done by Parameterized Constructor.  
 * @author priya.m
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuestionException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public QuestionException() {
		super();
	}
	public QuestionException(String errmsg) {
		super(errmsg);
	}

}
