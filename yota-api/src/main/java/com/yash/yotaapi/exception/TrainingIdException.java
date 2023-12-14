package com.yash.yotaapi.exception;
 
/**
 * This class is used to handle custom TrainingIdException
 * @author raghav.muchhal 
 * */
@SuppressWarnings("serial")
public class TrainingIdException extends RuntimeException {
 
    public TrainingIdException(String msg) {
        super(msg);
    }
}