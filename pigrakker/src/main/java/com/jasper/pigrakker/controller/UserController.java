package com.jasper.pigrakker.controller;

import com.jasper.pigrakker.model.User;
import com.jasper.pigrakker.repository.RoleRepository;
import com.jasper.pigrakker.repository.UserRepository;
import com.jasper.pigrakker.service.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final SecurityUserDetailsService securityUserDetailsService;
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserController(SecurityUserDetailsService securityUserDetailsService, UserRepository userRepository) {
        this.securityUserDetailsService = securityUserDetailsService;
        this.userRepository = userRepository;
    }
    @RequestMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("user/create");
        User user = new User();
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    @PostMapping("/register")
    public ModelAndView newUser(@Validated User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("alertMessage","Er klopt iets niet :(");
            modelAndView.setViewName("user/create");
            return modelAndView;
        }
        if(userRepository.findByUsernameOrEmail(user.getEmail(), user.getUsername()).isPresent()) {
            modelAndView.addObject("alertMessage", "Dit emailadres of gebruikersnaam bestaat al!");
            modelAndView.setViewName("user/create");
            return modelAndView;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        securityUserDetailsService.save(user);
        modelAndView.addObject("alertMessage", "Succesvol geregistreed!");
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }
    @RequestMapping("/me")
    public ModelAndView updateUserForm(Principal principal)
    {

        Optional<User> currentUser = userRepository.findByUsername(principal.getName());
        if(currentUser.isEmpty())
        {
            return new ModelAndView("redirect:/");
        }
        ModelAndView modelAndView = new ModelAndView("user/show");
        modelAndView.addObject("user", currentUser.get());
        return modelAndView;
    }
    @PostMapping("/me")
    public ModelAndView updateUser(@Validated User user, BindingResult bindingResult, Principal principal)
    {
        ModelAndView modelAndView = new ModelAndView("user/show");
        Optional<User> currentUser = userRepository.findByUsername(principal.getName());
        user.setPassword(currentUser.get().getPassword());
        if(bindingResult.hasErrors())
        {
            modelAndView.addObject("alertMessage","Er klopt iets niet :(");
            return modelAndView;
        }

        securityUserDetailsService.save(user);
        modelAndView.addObject("alertMessage","Succesvol gewijzigd");
        return  modelAndView;
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping("/users")

    public ModelAndView users() {
        ModelAndView modelAndView = new ModelAndView("/admin/users.html");
        modelAndView.addObject("users", userRepository.findAll());
        return modelAndView;
    }
}
