package com.yash.yotaapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This is global exception handler class which returns proper error message.
 * @author nitin.chougale
 *
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandle extends ResponseEntityExceptionHandler{
	
	
	/**
	 * 
	 * @param AssociateDetailsException,
	 * @param request
	 * @return This method handle Associate exception for POST,PUT request for AssociateDetailsController
	 */
	@ExceptionHandler
	public final ResponseEntity<?> handleAssociateException(AssociateDetailsException associateException, WebRequest request)
	{
		AssociateDetailsExceptionResponse response = new AssociateDetailsExceptionResponse(associateException.getMessage());
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}
	
	
}
