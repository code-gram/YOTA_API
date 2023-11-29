package com.yash.yotaapi.exception;

/**
 * Custom exception thrown when a requested unit is not found in the system.
 * @author pravin.navarkar
 */
public class UnitNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String message;

    public UnitNotFoundException() {};

    public UnitNotFoundException(String message){
        super(message);
        this.message = message;
    }
}
