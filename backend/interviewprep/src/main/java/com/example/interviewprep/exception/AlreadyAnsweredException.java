package com.example.interviewprep.exception;

public class AlreadyAnsweredException extends RuntimeException {

    public AlreadyAnsweredException(String message) {
        super(message);
    }
}