package com.jasper.pigrakker.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {

    @Value("${spring.application.name}")
    String appName;
    @GetMapping("/")
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView("view/viewPage");
        modelAndView.addObject("appName", appName);
        return modelAndView;
    }

}
