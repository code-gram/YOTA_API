package com.yash.yotaapi.exception;

/**
 * Custom Exception thrown when attempting to add a training that already exists in the system.
 * @author pravin.navarkar
 */
public class TrainingAlreadyExistsException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String message;

    public TrainingAlreadyExistsException() {}

    public TrainingAlreadyExistsException(String message){
        super(message);
        this.message = message;
    }
}
