package com.example.help_b.component.Enum;

public enum GeneralEnum {
    QUESTION(1),

    COMMENT(2),



    QUESTION_NOT_FOUND(0, "问题不存在！"),

    TARGET_PARAM_NOT_FOUND(1, "未选中任何评论进行回复！"),

    TYPE_PARAM_WRONG(2,"评论类型不存在!"),

    USER_NOT_LOGIN(3,"用户未登录！"),
    COMMENT_NOT_FOUND(4,"评论不存在!"),
    COMMENT_EMPTY(5,"评论为空！")
    ;

    GeneralEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
    GeneralEnum(int code){
        this.code=code;
    }

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
