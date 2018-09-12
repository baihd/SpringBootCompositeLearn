package com.composite.oauth2client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/redirect")
    public String redirect() {
        return "redirect";
    }

    @GetMapping("/password")
    public String password() {
        return "password";
    }

}
