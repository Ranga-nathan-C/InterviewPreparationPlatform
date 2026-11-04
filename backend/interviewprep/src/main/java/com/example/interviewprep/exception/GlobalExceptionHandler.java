package com.example.interviewprep.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex){
        return ResponseEntity
                            .status(HttpStatus.BAD_REQUEST)
                            .body(ex.getMessage());

    }

    @ExceptionHandler(AlreadyAnsweredException.class)
    public ResponseEntity<String> handleAlreadyAnsweredException(AlreadyAnsweredException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<String> handleQuestionNotFoundException(QuestionNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String>  handleUserNotFoundException(UserNotFoundException ex){
            return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(ex.getMessage()); 
    }

}
