package com.composite.controller;

import com.composite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/hello")
    public Map<String, Object> testUser(Map<String, Object> params) {
        try {
            userService.insert(params);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @ResponseBody
    @RequestMapping(value = "/user/{name}")
    public Map<String, Object> getUser(@PathVariable String name) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Map<String, Object>> userMaps = userService.selectUsersByName(name);
            if (userMaps != null && userMaps.size() != 0) {
                map = userMaps.get(0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return map;
    }


}
