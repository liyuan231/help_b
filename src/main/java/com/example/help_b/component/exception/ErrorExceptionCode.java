package com.example.help_b.component.exception;

public enum ErrorExceptionCode{
    QUESTION_NOT_FOUND("问题不存在");
    private String message;
    ErrorExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
