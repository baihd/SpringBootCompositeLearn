package com.composite.cxfserver.base;

import java.io.Serializable;

public class ResponseBean implements Serializable {
    public static final String SUCCESS_CODE = "SUCCESS";
    public static final String FAIL_CODE = "FAIL";
    private Object data;
    private String code;
    private String error;

    public static ResponseBean success(Object data) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setData(data);
        responseBean.setCode(SUCCESS_CODE);
        responseBean.setError("");
        return responseBean;
    }

    public static ResponseBean fail(String error) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setData("");
        responseBean.setCode(FAIL_CODE);
        responseBean.setError(error);
        return responseBean;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
