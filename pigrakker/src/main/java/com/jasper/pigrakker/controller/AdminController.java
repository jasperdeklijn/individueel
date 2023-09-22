package com.jasper.pigrakker.controller;

import com.jasper.pigrakker.model.User;
import com.jasper.pigrakker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserRepository userRepository;

    @Secured("ROLE_ADMIN")
    @RequestMapping("/home")
    public ModelAndView adminHome() {
        return new ModelAndView("/admin/home.html");
    }
}
