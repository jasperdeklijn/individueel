package com.jasper.pigrakker.controller;

import com.jasper.pigrakker.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@Controller
public class MainController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView("view/index");
        modelAndView.addObject("products", productRepository.findAll());
        return modelAndView;
    }
    @GetMapping("/onsdoel")
    public  ModelAndView onsDoel () {
        return  new ModelAndView("view/onsDoel");
    }

}
