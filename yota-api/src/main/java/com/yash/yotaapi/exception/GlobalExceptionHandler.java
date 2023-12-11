package com.yash.yotaapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

	@ExceptionHandler(BatchNotFoundException.class)
	public ResponseEntity<ExceptionResponse> BatchNotFoundException(BatchNotFoundException e, WebRequest request) {

		ExceptionResponse error = new ExceptionResponse(e.getMessage());
		return new ResponseEntity<ExceptionResponse>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BatchIdException.class)
	public ResponseEntity<ExceptionResponse> handelBatchException(BatchIdException e, WebRequest request) {

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

	/**
	 * Custom exception handler method to handle {@link MethodArgumentNotValidException}.
	 *
	 * This method is triggered when validation on a method argument fails. It generates a custom
	 * {@link ResponseEntity} with an {@link ExceptionResponse} containing a predefined error message
	 * indicating that the field name cannot be empty or null.
	 *
	 * @param ex The {@link MethodArgumentNotValidException} instance representing the validation failure.
	 * @param headers The headers for the response.
	 * @param status The HTTP status for the response.
	 * @param request The incoming web request.
	 * @return A {@link ResponseEntity} with an {@link ExceptionResponse} containing the error details.
	 *
	 * @author pravin.navarkar
	 */
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse error = new ExceptionResponse("Field name can not be empty or null");
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Custom exception handler method to handle {@link TrainingAlreadyExistsException}.
	 *
	 * This method catches exceptions to type {@link TrainingAlreadyExistsException} and generates a custom
	 * {@link ResponseEntity} with an {@link ExceptionResponse} containing the error message.
	 *
	 * @param e The {@link TrainingAlreadyExistsException} instance to handle.
	 * @param request The incoming web request.
	 * @return A {@link ResponseEntity} with an {@link ExceptionResponse} containing the error details.
	 *
	 * @author pravin.navarkar
	 */
	@ExceptionHandler(TrainingAlreadyExistsException.class)
	public ResponseEntity<ExceptionResponse> handleTrainingAlreadyExistsException(TrainingAlreadyExistsException e, WebRequest request) {
		ExceptionResponse error = new ExceptionResponse(e.getMessage());
		return new ResponseEntity<ExceptionResponse>(error, HttpStatus.BAD_REQUEST);
	}
}	
