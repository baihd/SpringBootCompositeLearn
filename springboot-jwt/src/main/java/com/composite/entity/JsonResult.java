package com.composite.entity;

import java.io.Serializable;

public class JsonResult implements Serializable {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;

    private int status;
    /**
     * 错误消息
     */
    private String message;
    /**
     * 返回正确时候的数据
     */
    private Object data;

    public JsonResult() {
    }

    public JsonResult(String error) {
        status = ERROR;
        this.message = error;
    }

    public JsonResult(Object data) {
        status = SUCCESS;
        this.data = data;
    }

    public JsonResult(Throwable e) {
        status = ERROR;
        message = e.getMessage();
    }

    public JsonResult(int state, Throwable e) {
        this.status = state;
        this.message = e.getMessage();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int state) {
        this.status = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult [status=" + status + ", message=" + message + ", data=" + data + "]";
    }
}
