package com.composite.oauth2client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/redirect")
    public String redirect(HttpServletRequest request, RedirectAttributes attributes) {
        return "redirect:/index";
    }

    @GetMapping("/password")
    public String password() {
        return "password";
    }

    @GetMapping("/index")
    public String index(HttpServletRequest request) {
        return "index";
    }

}
