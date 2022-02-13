package com.walkhhh.community.dto;

import com.walkhhh.community.exception.CustomizeErrorCode;
import com.walkhhh.community.exception.CustomizeException;
import lombok.Data;

/**
 * @author HUANG BAIRUI
 * @create 2022-02-13 9:50
 */
@Data
public class ResultDTO<T> {
    private Integer code;
    private String message;
    private T data;

    public static ResultDTO errorOf(CustomizeException e){
        return errorOf(e.getCode(), e.getMessage());
    }

    public static ResultDTO errorOf(Integer code, String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode customizeErrorCode){
        return errorOf(customizeErrorCode.getCode(), customizeErrorCode.getMessage());
    }


    public static ResultDTO okOf(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    public static <T> ResultDTO okOf(T data){
        ResultDTO resultDTO = okOf();
        resultDTO.setData(data);
        return resultDTO;
    }
}
