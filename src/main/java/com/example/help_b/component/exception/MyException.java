package com.example.help_b.component.exception;

import com.example.help_b.component.Enum.GeneralEnum;

public class MyException extends RuntimeException{
    private Integer code;
    private String message;
    public MyException(GeneralEnum generalEnum){
        this.message=generalEnum.getMessage();
        this.code=generalEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
