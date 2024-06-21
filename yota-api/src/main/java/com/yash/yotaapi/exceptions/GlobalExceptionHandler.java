package com.yash.yotaapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Project Name - YOTASecurityAPI
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yash.raj
 * @since - 02-04-2024
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<String> handleApplicationException(ApplicationException applicationException) {
        String message = applicationException.getMessage();
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserUnauthorizedException.class)
    public ResponseEntity<String> handleUserUnauthorizedException(UserUnauthorizedException unauthorizedException) {
        String message = unauthorizedException.getMessage();
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TechnologyAlreadyAvailableException.class)
    public ResponseEntity<String> handleTechnologyAlreadyAvailableException(TechnologyAlreadyAvailableException technologyAlreadyAvailableException) {
        String message = technologyAlreadyAvailableException.getMessage();
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TestAvailableException.class)
    public ResponseEntity<String> handleTestAvailableException(TestAvailableException testAvailableException) {
        String message = testAvailableException.getMessage();
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<String> handlePasswordMismatchException(PasswordMismatchException passwordMismatchException) {
        String message = passwordMismatchException.getMessage();
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<String> handleQuestionNotFoundException(QuestionNotFoundException questionNotFoundException) {
        String message = questionNotFoundException.getMessage();
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(QuestionDeletionException.class)
    public ResponseEntity<String> handleQuestionDeletionException(QuestionDeletionException questionDeletionException) {
        String message = questionDeletionException.getMessage();
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        String message = resourceNotFoundException.getMessage();
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TrainingException.class)
    public ResponseEntity<Map<String, Object>> handleTrainingException(TrainingException trainingException) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", trainingException.getMessage());
        map.put("status", trainingException.getHttpStatus());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
