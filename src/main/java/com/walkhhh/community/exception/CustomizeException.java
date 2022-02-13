package com.walkhhh.community.exception;

/**
 * @author HUANG BAIRUI
 * @create 2022-02-13 9:43
 */
public class CustomizeException extends RuntimeException{
    private String message;
    private Integer code;

    public CustomizeException(String message, Integer code){
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
