package com.composite.master.domain.controller;

import com.composite.common.Const;
import com.composite.entity.User;
import com.composite.param.LoginParam;
import com.composite.redis.RedisService;
import com.composite.redis.UserKey;
import com.composite.result.Result;
import com.composite.service.UserService;
import com.composite.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public Result<User> doLogin(HttpServletResponse response, HttpSession session, @Valid LoginParam loginParam) {
        Result<User> login = userService.login(loginParam);
        if (login.isSuccess()) {
            CookieUtil.writeLoginToken(response, session.getId());
            redisService.set(UserKey.getByName, session.getId(), login.getData(), Const.RedisCacheExtime.REDIS_SESSION_EXTIME);
        }
        return login;
    }

    @RequestMapping("/logout")
    public String doLogout(HttpServletRequest request, HttpServletResponse response) {
        String token = CookieUtil.readLoginToken(request);
        CookieUtil.delLoginToken(request, response);
        redisService.del(UserKey.getByName, token);
        return "login";
    }
}
