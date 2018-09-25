package com.composite.oauth2client.service.impl;

import com.composite.oauth2client.dao.UserDao;
import com.composite.oauth2client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Map<String, Object> findUserByName(String userName) {
        return userDao.findUserByName(userName);
    }

    @Override
    public Map<String, Object> findUserByOtherUserName(String userName) {
        return userDao.findUserByOtherUserName(userName);
    }

    @Override
    public int saveUser(Map<String, Object> userMap) {
        return userDao.saveUser(userMap);
    }
}
