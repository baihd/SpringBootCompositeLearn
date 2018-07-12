package com.composite.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.composite.dubbo.DubboService;
import com.composite.entity.User;

@Service(version = "1.0.0")
public class DubboServiceImpl implements DubboService {

    @Override
    public User findUser(String username) {
        User user = new User();
        user.setId(1);
        user.setUsername("username");
        return user;
    }
}
