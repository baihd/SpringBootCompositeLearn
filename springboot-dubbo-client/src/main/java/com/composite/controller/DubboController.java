package com.composite.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.composite.dubbo.DubboService;
import com.composite.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dubbo")
public class DubboController {

    @Reference(version = "1.0.0")
    DubboService dubboService;

    @RequestMapping("findUser")
    public void findUser() {
        System.out.println("dubbo开始调用");
        User user = dubboService.findUser("username");
        System.out.println(user.toString());
    }
}
