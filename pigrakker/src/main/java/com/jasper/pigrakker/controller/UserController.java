package com.jasper.pigrakker.controller;

import com.jasper.pigrakker.model.User;
import com.jasper.pigrakker.repository.RoleRepository;
import com.jasper.pigrakker.repository.UserRepository;
import com.jasper.pigrakker.service.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView register()
    {
        ModelAndView modelAndView = new ModelAndView("user/create");
        User user = new User();
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    @PostMapping("/register")
    public ModelAndView newUser(@Validated User user, BindingResult bindingResult, ModelMap model)
    {
        if (bindingResult.hasErrors() || !isEmailandUsernameUnique(user.getEmail() ,user.getUsername())) {
            // Handle validation errors or duplicate email/username
            model.addAttribute("alertMessage", "Registration failed. Please check your inputs.");
            return new ModelAndView("user/create", model); // Assuming you have a registration page.
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        securityUserDetailsService.save(user);
        model.addAttribute("alertMessage", "Succesvol geregistreed!");
        return new ModelAndView("redirect:/login",model);
    }
    private boolean isEmailandUsernameUnique(String email,String username) {
        Optional<User> user = userRepository.findByUsernameOrEmail(email, username);
        return  user.isPresent();
    }


}
