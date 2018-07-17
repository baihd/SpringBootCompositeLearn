package com.composite.controller;

import com.composite.service.JtaTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private JtaTestService jtaTestService;

    @Transactional
    @RequestMapping("/insert")
    public void test() {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("id", "2");
            jtaTestService.test(map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
