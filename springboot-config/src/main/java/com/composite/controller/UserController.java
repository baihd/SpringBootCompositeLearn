package com.composite.controller;

import com.composite.config.PersonConfig;
import com.composite.config.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties({PersonConfig.class, UserConfig.class})
public class UserController {
    @Autowired
    private PersonConfig personConfig;

    @Autowired
    private UserConfig userConfig;

    @Value("${person.name}")
    private String name;

    @Value("${person.age}")
    private String age;

    @RequestMapping(value = "/searchPersonName")
    public String searchPersonName() {
        String name1 = name;
        return name1;
    }

    @RequestMapping(value = "/searchPerson")
    public String searchPerson() {
        String value = personConfig.getValue();
        return value;
    }

    @RequestMapping(value = "/searchUser")
    public String searchUser() {
        String value = userConfig.getName();
        return value;
    }

}
