package com.yash.yotaapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This is global exception handler class which returns proper error message for all controller requests..
 * @author nitin.chougale
 *
 */
@ControllerAdvice
public class GlobalExceptionHandle extends ResponseEntityExceptionHandler{
	
	
	/**
	 * This method handles Associate exception for AssociateDetailsController.
	 * @param associateException
	 * @param request
	 */
	@ExceptionHandler
	public final ResponseEntity<?> handleAssociateException(AssociateDetailsException associateException, WebRequest request)
	{
		AssociateDetailsExceptionResponse response = new AssociateDetailsExceptionResponse(associateException.getMessage());
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * This method handles AssociateDetailsNotFoundException for AssociateDetailsController.
	 * @param associateException
	 * @param request
	 */
	@ExceptionHandler
	public final ResponseEntity<?> handleAssociateDetailsNotFoundException(AssociateDetailsNotFoundException associateException, WebRequest request)
	{
		AssociateDetailsExceptionResponse response = new AssociateDetailsExceptionResponse(associateException.getMessage());
		return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
	}
}