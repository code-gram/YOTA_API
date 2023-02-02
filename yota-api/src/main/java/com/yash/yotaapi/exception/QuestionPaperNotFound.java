package com.yash.yotaapi.exception;

/**
 * This Is Unchecked Exception 
 * Uses Parameterized Constructor To Create Customized Error Message
 */
public class QuestionPaperNotFound extends RuntimeException {
	
	 public QuestionPaperNotFound(String msg) {
		 super(msg);
	 }

}

