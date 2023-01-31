package com.yash.yotaapi.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(TestNotFound.class)
	 public ResponseEntity<ErrorResponse> TestNotFound(TestNotFound e,HttpServletRequest request){
	
    ErrorResponse error=new ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, e.getMessage(), new Date(), request.getRequestURI());
	return new ResponseEntity<ErrorResponse>(error,HttpStatus.NOT_FOUND);

}
}