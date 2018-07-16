package com.composite.controller;

import com.composite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public String insert() {
        Map<String, Object> map = new HashMap<>();
        try {
            userService.insertUser(map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "success";
    }

    @RequestMapping("/delete")
    public String delete() {
        Map<String, Object> map = new HashMap<>();
        try {
            userService.deleteUser(map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "success";
    }

    @RequestMapping("/update")
    public String update() {
        Map<String, Object> map = new HashMap<>();
        try {
            userService.updateUser(map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "success";
    }

    @RequestMapping("/select")
    public String select() {
        Map<String, Object> map = new HashMap<>();
        try {
            userService.searchUser(map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "success";
    }

}
