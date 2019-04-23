package com.composite.master.domain.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(
            @RequestParam(value = "username") String userName,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "rememberMe", defaultValue = "false") boolean rememberMe) {
        logger.info("==========" + userName + password + rememberMe);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        token.setRememberMe(rememberMe);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            logger.error("账号或密码输入错误" + e.getMessage());
            return "{\"Msg\":\"您的账号或密码输入错误\",\"state\":\"failed\"}";
        }
        return "{\"Msg\":\"登陆成功\",\"state\":\"success\"}";
    }

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "no permission";
    }

}
