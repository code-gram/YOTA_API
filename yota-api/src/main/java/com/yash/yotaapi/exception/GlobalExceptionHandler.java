package com.yash.yotaapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
/**
 * This is global exception handler class which returns proper error message
 * 
 * @author Raghav Muchhal
 *
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ParentTechnologyException.class)
	public final ResponseEntity<?> handleTechnologyException(ParentTechnologyException technologyException,
			WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(technologyException.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ParentTechnologyNotFoundException.class)
	public final ResponseEntity<?> handleTechnologyNotFoundException(
			ParentTechnologyNotFoundException technologyException, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(technologyException.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(QuestionException.class)
	public final ResponseEntity<?> handleQuestionException(QuestionException questionException, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(questionException.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AssociateDetailsException.class)
	public final ResponseEntity<?> handleAssociateException(AssociateDetailsException associateException,
			WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(associateException.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AssociateDetailsNotFoundException.class)
	public final ResponseEntity<?> handleAssociateDetailsNotFoundException(
			AssociateDetailsNotFoundException associateException, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(associateException.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TrainingNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleTrainingNotFoundException(TrainingNotFoundException e,
			WebRequest request) {
		ExceptionResponse error = new ExceptionResponse(e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TrainingIdException.class)
	public ResponseEntity<ExceptionResponse> handleTrainingIdException(TrainingIdException e, WebRequest request) {
		ExceptionResponse error = new ExceptionResponse(e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DateInValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ExceptionResponse> handleDateInValidException(DateInValidException e, WebRequest request) {
		ExceptionResponse error = new ExceptionResponse(e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<ExceptionResponse> handleNoDataFoundException(NoDataFoundException e, WebRequest request) {
		ExceptionResponse error = new ExceptionResponse(e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ClientNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleClientNotFoundException(ClientNotFoundException e,
			WebRequest request) {
		ExceptionResponse error = new ExceptionResponse(e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TestNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleTestNotFoundException(TestNotFoundException e, WebRequest request) {
        ExceptionResponse error = new ExceptionResponse(e.getMessage());
        return new ResponseEntity<ExceptionResponse>(error, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(TestAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleTestAlreadyExistsException(TestAlreadyExistsException e, WebRequest request) {
        ExceptionResponse error = new ExceptionResponse(e.getMessage());
        return new ResponseEntity<ExceptionResponse>(error, HttpStatus.BAD_REQUEST);
    }
}
