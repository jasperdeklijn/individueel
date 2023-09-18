package com.jasper.pigrakker.controller;

import com.jasper.pigrakker.model.User;
import com.jasper.pigrakker.repository.RoleRepository;
import com.jasper.pigrakker.repository.UserRepository;
import com.jasper.pigrakker.service.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
public class UserController {
    private final SecurityUserDetailsService securityUserDetailsService;

    @Autowired
    public UserController(SecurityUserDetailsService securityUserDetailsService) {
        this.securityUserDetailsService = securityUserDetailsService;
    }
    @RequestMapping("/register")
    public ModelAndView register()
    {
        ModelAndView modelAndView = new ModelAndView("user/create");
        User user = new User();
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    @PostMapping("/register")
    public ModelAndView newUser(User user)
    {
        //encode password ?!?!?!?
        securityUserDetailsService.saveOrUpdate(user);
        ModelAndView modelAndView = new ModelAndView("user/create");
        User newUser = new User();
        modelAndView.addObject("user", newUser);
        return modelAndView;
    }

}
