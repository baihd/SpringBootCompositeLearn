package com.composite.dockercompose.controller;

import com.composite.dockercompose.dao.UserDao;
import com.composite.dockercompose.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public void saveUser(@RequestParam String name) {
        User user = new User();
        user.setId(1);
        user.setName(name);
        user.setAge(20);
        userDao.save(user);
    }

    @RequestMapping(value = "/findUser", method = RequestMethod.POST)
    public User findUser() {
        List<User> userList = userDao.findAll();
        return userList.get(0);
    }

}
