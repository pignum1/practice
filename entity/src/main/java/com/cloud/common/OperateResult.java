package com.cloud.common;

import java.io.Serializable;

/**
 * @author WXY
 * @ClassName OperateResult
 * @Description 结果返回工具
 * @Date 2019/6/25 18:03
 * @Version 1.0
 **/
public class OperateResult implements Serializable{
    private String message;

    private String status;

    public static final OperateResult operationSuccess(String message){
        return new OperateResult(message,"成功");
    }

    public static final OperateResult operationFailure(String message){
        return new OperateResult(message,"失败");
    }

    public OperateResult(String message,String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}