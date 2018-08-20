package com.composite.controller;

import com.composite.entity.User;
import com.composite.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping(value = "/userInfo")
    @ResponseBody
    public User getUserInfo(@RequestParam("username") String username) {
        logger.debug("second provider");
        logger.info("username:" + username);
        User user = userService.findUserInfo(username);
        if (user != null) {
            logger.info(user.toString());
        }
        return user;
    }
}
