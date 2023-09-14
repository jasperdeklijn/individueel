package com.Jasper.pigrakker.controller;

import com.Jasper.pigrakker.model.User;
import com.Jasper.pigrakker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@Controller
public class MainController {

    @GetMapping("/")
    public ModelAndView homePage(Principal principal) {

        ModelAndView modelAndView = new ModelAndView("view/index");
        if(principal != null)
        {
            modelAndView.addObject("username", principal.getName());
        }

        return modelAndView;
    }


}
