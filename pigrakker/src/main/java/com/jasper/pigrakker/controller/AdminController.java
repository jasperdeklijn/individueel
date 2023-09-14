package com.Jasper.pigrakker.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Secured("ROLE_ADMIN")
    @RequestMapping("/home")
    public ModelAndView adminHome() {
        ModelAndView modelAndView = new ModelAndView("/admin/home.html");
        return modelAndView;
    }
}
