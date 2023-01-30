package com.yash.yotaapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**This is global exception handler class which returns proper error message
 * @author pratik.kurbet
 *
 */
@ControllerAdvice
@RestController
public class CustomResposeEntityExceptionHandler extends ResponseEntityExceptionHandler
{
	/**
	 * 
	 * @param technologyException,
	 * @param request
	 * @return This method handle technology exception for POST,PUT request for ParentTechnologyController
	 */
	@ExceptionHandler
	public final ResponseEntity<?> handleTechnologyException(ParentTechnologyException technologyException,WebRequest request)
	{
		ParentTechnologyExceptionResponse response= new ParentTechnologyExceptionResponse(technologyException.getMessage());
		return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
	}
	/**
	 * 
	 * @param technologyException,
	 * @param request
	 * @return This method handle technology not found exception for GET request for ParentTechnologyController
	 */
	@ExceptionHandler
	public final ResponseEntity<?> handleTechnologyNotFoundException(ParentTechnologyNotFoundException technologyException,WebRequest request)
	{
		ParentTechnologyExceptionResponse response= new ParentTechnologyExceptionResponse(technologyException.getMessage());
		return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
	}
}
