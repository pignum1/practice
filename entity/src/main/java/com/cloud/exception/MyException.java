package com.cloud.exception;

/**
 * @author WXY
 * @ClassName MyException
 * @Description 自定义异常处理
 * @Date 2019/8/4 20:37
 * @Version 1.0
 **/
public class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
}