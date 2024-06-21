package com.yash.yotaapi.exceptions;

public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException(String message){
        super(message);
    }
}