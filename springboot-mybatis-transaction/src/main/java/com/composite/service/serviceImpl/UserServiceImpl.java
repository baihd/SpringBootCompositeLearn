package com.composite.service.serviceImpl;

import com.composite.dao.UserDao;
import com.composite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(value = "transactionManager1")
    public int insert(Map<String, Object> userMap) throws Exception {
        userMap.put("userName", "name7");
        userMap.put("password", "password7");
        userMap.put("phone", "phone7");
        userDao.insert(userMap);
        return 0;
    }

    @Override
    public List<Map<String, Object>> selectUsers() {
        return userDao.selectUsers();
    }
}
