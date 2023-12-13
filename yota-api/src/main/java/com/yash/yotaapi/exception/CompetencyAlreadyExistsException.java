package com.yash.yotaapi.exception;

public class CompetencyAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    private String message;

    public CompetencyAlreadyExistsException() {}

    public CompetencyAlreadyExistsException(String message){
            super(message);
            this.message = message;
    }

}
