package com.example.interviewprep.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message){
        super(message);
    }
}
