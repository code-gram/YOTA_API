package com.yash.yotaapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This is global exception handler class which returns proper error message
 * @author priya.m
 *
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * 
	 * @param Question Exception
	 * @param request
	 * @return This method handle question exception for POST,PUT request for QuestionController
	 */
	@ExceptionHandler
	public final ResponseEntity<?> handleQuestionException(QuestionException questionException, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(questionException.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
		}

}
