package com.yash.yotaapi.exception;

/**
 * Custom exception thrown when a requested training is not found in the system.
 * @author pravin.navarkar
 */
public class TrainingNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String message;

    public TrainingNotFoundException() {};

    public TrainingNotFoundException(String message){
        super(message);
        this.message = message;
    }
}
