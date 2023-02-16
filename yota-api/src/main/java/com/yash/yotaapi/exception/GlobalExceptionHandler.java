package com.yash.yotaapi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/*This is Global exception handler class used handle exception with proper error message.
 * @author anil.shimpi
 * */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BatchNotFoundException.class)
	public ResponseEntity<ExceptionResponse> BatchNotFoundException(BatchNotFoundException e, WebRequest request){
		
		ExceptionResponse error=new ExceptionResponse(e.getMessage());
		return new ResponseEntity<ExceptionResponse>(error,HttpStatus.NOT_FOUND);
	}
	
	

}
