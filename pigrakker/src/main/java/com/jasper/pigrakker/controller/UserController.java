package com.jasper.pigrakker.controller;

import com.jasper.pigrakker.model.User;
import com.jasper.pigrakker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserRepository userRepository;
    @RequestMapping("/register")
    public ModelAndView register()
    {
        ModelAndView modelAndView = new ModelAndView("user/create");
        User user = new User();
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    @PostMapping("/register")
    public String submut(@Validated User user)
    {

        userRepository.save(user);

        return "index";
    }

}
