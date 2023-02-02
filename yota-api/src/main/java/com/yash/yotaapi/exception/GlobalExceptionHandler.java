package com.yash.yotaapi.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This Is Global Exception Handler Class Which Returns Proper Error Message For All Controller Requests 
 * @author himanshu.pednekar
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 *This Method Handles Exception For QuestionPaperController
	 */
	@ExceptionHandler(QuestionPaperNotFound.class)
	 public ResponseEntity<ErrorResponse> TestNotFound(QuestionPaperNotFound e,HttpServletRequest request){
	
    ErrorResponse error=new ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, e.getMessage(), new Date(), request.getRequestURI());
	return new ResponseEntity<ErrorResponse>(error,HttpStatus.NOT_FOUND);

}
}