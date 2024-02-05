package com.yash.yotaapi.exception;

/**
 * Custom Exception thrown when attempting to add a unit that already exists in the system.
 * @author pravin.navarkar
 */
public class UnitAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String message;

    public UnitAlreadyExistsException() {}

    public UnitAlreadyExistsException(String message){
            super(message);
            this.message = message;
    }
}
