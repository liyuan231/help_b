package com.example.help_b.component.exception;

import lombok.Data;

public class QuestionException extends RuntimeException{
    private String message;
    public QuestionException(ErrorExceptionCode errorExceptionCode){
        super(errorExceptionCode.getMessage());
        this.message=errorExceptionCode.getMessage();
    }
}
