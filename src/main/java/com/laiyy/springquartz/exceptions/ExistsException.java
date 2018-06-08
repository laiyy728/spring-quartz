package com.laiyy.springquartz.exceptions;

/**
 * @author laiyy
 * @date 2018/6/8 11:35
 * @description 自定义异常： 实体已存在
 */
public class ExistsException extends RuntimeException{

    public ExistsException() {
    }

    public ExistsException(String message) {
        super(message);
    }

    public ExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistsException(Throwable cause) {
        super(cause);
    }

    public ExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}


