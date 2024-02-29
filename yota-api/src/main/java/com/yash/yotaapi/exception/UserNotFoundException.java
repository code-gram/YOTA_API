package com.yash.yotaapi.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String message;

    public UserNotFoundException() {};

    public UserNotFoundException(HttpStatus httpStatus, String message) {
        super(message);
        this.message = message;
    }
}

