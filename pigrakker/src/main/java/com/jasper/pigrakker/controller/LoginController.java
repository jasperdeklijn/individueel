package com.jasper.pigrakker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.lang.annotation.Annotation;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login() {
        return "login.";
    }
}
