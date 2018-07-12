package com.composite.controller;

import com.composite.dao.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
    @Autowired
    private RedisDao redisDao;

    @ResponseBody
    @RequestMapping(value = "setKey")
    public void setKey() {
        redisDao.setKey("name", "name1");
        redisDao.setKey("age", "28");
    }

    @ResponseBody
    @RequestMapping(value = "getValue")
    public String getValue() {
        String value1 = redisDao.getValue("name");
        String value2 = redisDao.getValue("age");
        return value1 + value2;
    }
}
