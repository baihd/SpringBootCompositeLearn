package com.composite.master.domain.controller;

import com.composite.entity.User;
import com.composite.service.MoreDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/moreData")
public class MoreDataController {

    @Autowired
    private MoreDataService moreDataService;

    @RequestMapping(value = "/getDb1AllUser")
    public List<Map> getDb1AllUser() {
        List<Map> list = moreDataService.getAllUser1();
        return list;
    }

    @RequestMapping(value = "/getDb2AllUser")
    public List<Map> getDb2AllUser() {
        List<Map> list = moreDataService.getAllUser2();
        return list;
    }

    @RequestMapping(value = "/addDb1User")
    public String addDb1User(String name) {
        User user = new User("name11", new Date());
        Long rows = moreDataService.addUserGetID1(user);//返回的是结果行数
        return "{id:" + user.getId() + "}";
    }

    @RequestMapping(value = "/addDb2User")
    public String addDb2User(String name) {
        User user = new User("name2", new Date());
        Long rows = moreDataService.addUserGetID2(user);
        return "{id:" + user.getId() + "}";
    }
}
