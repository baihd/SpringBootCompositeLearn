package com.composite.controller;

import com.composite.entity.User;
import com.composite.producer.RabbitMqProducer;
import com.composite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RabbitMqUserController {

    @Autowired
    private RabbitMqProducer rabbitMqSender;

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/getUser")
    public String getUser() {
        User user = userService.selectUser("1");
        Object object = rabbitMqSender.sendAndReceiveDirect("DIRECTQUEUEKEY", user);
        byte[] bytes = (byte[]) object;
        User resultUser = (User) SerializationUtils.deserialize(bytes);
        System.out.println(resultUser);
        return resultUser.toString();
    }
}
