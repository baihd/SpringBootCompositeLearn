package com.composite.entity;

/*
 * 相应结果枚举类
 */
public enum ResultStatusCode {
    OK(0, "OK"),
    SYSTEM_ERR(-1, "System Error"),
    PERMISSION_DENIED(-100, "Permission Denied"),
    INVALID_CLIENTID(-101, "Invalid clientid"),
    INVALID_PASSWORD(-102, "User name or password is incorrect"),
    INVALID_CAPTCHA(-103, "Invalid captcha or captcha overdue"),
    INVALID_TOKEN(-104, "Invalid token"),
    INVALID_USER_TOKEN(-105, "Invalid token");

    private int errorCode;
    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    private ResultStatusCode(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

}
