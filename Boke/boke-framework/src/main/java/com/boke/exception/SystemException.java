package com.boke.exception;

import com.boke.enums.AppHttpCodeEnum;

/**
 * @author DreamRay
 * @date 2023/8/23 12:48
 * @mood 自定义异常
 */
public class SystemException extends RuntimeException{
    private int code;

    private String msg;

    public int getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum){
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }
}
