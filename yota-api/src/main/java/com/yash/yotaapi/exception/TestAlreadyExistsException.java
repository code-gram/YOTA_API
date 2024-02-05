package com.yash.yotaapi.exception;

public class TestAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    private String message;

    public TestAlreadyExistsException() {}

    public TestAlreadyExistsException(String message){
            super(message);
            this.message = message;
    }

}
