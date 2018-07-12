package com.composite.service.impl;

import com.composite.dao.UserDao;
import com.composite.entity.User;
import com.composite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User selectUser(String id) {
        return userDao.selectUser(id);
    }
}
