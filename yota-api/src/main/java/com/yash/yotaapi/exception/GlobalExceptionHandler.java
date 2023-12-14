package com.yash.yotaapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This is global exception handler class which returns proper error message
 * 
 * @author pratik.kurbet
 *
 */
@ControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	/**
	 * 
	 * @param technologyException,
	 * @param request
	 * @return This method handle technology exception for POST,PUT request for
	 *         ParentTechnologyController
	 */
	@ExceptionHandler
	public final ResponseEntity<?> handleTechnologyException(ParentTechnologyException technologyException,
			WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(technologyException.getMessage());
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param technologyException,
	 * @param request
	 * @return This method handle technology not found exception for GET request for
	 *         ParentTechnologyController
	 */
	@ExceptionHandler
	public final ResponseEntity<?> handleTechnologyNotFoundException(
			ParentTechnologyNotFoundException technologyException, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(technologyException.getMessage());
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param questionException
	 * @param request
	 * @return This method handle question exception for POST,PUT request for
	 *         QuestionController
	 */
	@ExceptionHandler
	public final ResponseEntity<?> handleQuestionException(QuestionException questionException, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(questionException.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method handles Associate exception for AssociateDetailsController.
	 * 
	 * @param associateException
	 * @param request
	 */
	@ExceptionHandler
	public final ResponseEntity<?> handleAssociateException(AssociateDetailsException associateException,
			WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(associateException.getMessage());
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method handles AssociateDetailsNotFoundException for
	 * AssociateDetailsController.
	 * 
	 * @param associateException
	 * @param request
	 */
	@ExceptionHandler
	public final ResponseEntity<?> handleAssociateDetailsNotFoundException(
			AssociateDetailsNotFoundException associateException, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(associateException.getMessage());
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TrainingNotFoundException.class)
	public ResponseEntity<ExceptionResponse> TrainingNotFoundException(TrainingNotFoundException e,
			WebRequest request) {

		ExceptionResponse error = new ExceptionResponse(e.getMessage());
		return new ResponseEntity<ExceptionResponse>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TrainingIdException.class)
	public ResponseEntity<ExceptionResponse> handelBatchException(TrainingIdException e, WebRequest request) {

		ExceptionResponse error = new ExceptionResponse(e.getMessage());
		return new ResponseEntity<ExceptionResponse>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DateInValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ExceptionResponse> handelDateInValidException(DateInValidException e, WebRequest request) {

		ExceptionResponse error = new ExceptionResponse(e.getMessage());
		return new ResponseEntity<ExceptionResponse>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<ExceptionResponse> noDataFoundException(NoDataFoundException e, WebRequest request) {

		ExceptionResponse error = new ExceptionResponse(e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ClientNotFoundException.class)
	public ResponseEntity<ExceptionResponse> ClientNotFoundException(ClientNotFoundException e, WebRequest request) {

		ExceptionResponse error = new ExceptionResponse(e.getMessage());
		return new ResponseEntity<ExceptionResponse>(error, HttpStatus.BAD_REQUEST);
	}
}
