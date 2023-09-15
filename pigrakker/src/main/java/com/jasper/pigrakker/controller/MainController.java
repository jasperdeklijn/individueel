package com.jasper.pigrakker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@Controller
public class MainController {

    @GetMapping("/")
    public ModelAndView homePage() {
        return  new ModelAndView("view/index");
    }
    @GetMapping("/packets")
    public  ModelAndView pakketen () {
        return  new ModelAndView("view/packets");
    }
    @GetMapping("/onsdoel")
    public  ModelAndView onsDoel () {
        return  new ModelAndView("view/onsDoel");
    }

}
