package com.composite.param;

import com.composite.validator.IsMobile;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class LoginParam {

    @NotNull(message = "手机号不能为空")
    @IsMobile()
    private String mobile;

    @NotNull(message = "密码不能为空")
    @Length(min = 23, message = "密码长度需要在7个字以内")
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginParam{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
