package com.yash.yotaapi.exception;

import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*This is Global exception handler class used handle exception with proper error message.
 * @author anil.shimpi
 * */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BatchNotFoundException.class)
	public ResponseEntity<ErrorResponse> BatchNotFoundException(BatchNotFoundException e,HttpServletRequest request){
		
		ErrorResponse error=new ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, e.getMessage(), new Date(), request.getRequestURI());
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuchElementFoundException.class)
	public ResponseEntity<ErrorResponse> NoSuchElementFound(NoSuchElementFoundException e,HttpServletRequest request){
		
		ErrorResponse error=new ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, e.getMessage(), new Date(), request.getRequestURI());
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.NOT_FOUND);
	}
	

}
