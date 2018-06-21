package com.laiyy.springquartz.exceptions;

/**
 * @author laiyy
 * @date 2018/6/21 11:17
 * @description 自定义异常：空值异常，与 NPE 区分开
 */
public class NullValueException extends RuntimeException {

    public NullValueException() {
        super();
    }

    public NullValueException(String message) {
        super(message);
    }

    public NullValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullValueException(Throwable cause) {
        super(cause);
    }

    protected NullValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
