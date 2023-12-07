package com.jasper.pigrakker.controller;

import com.jasper.pigrakker.config.CustomUserDetails;
import com.jasper.pigrakker.model.User;
import com.jasper.pigrakker.repository.PacketRepository;
import com.jasper.pigrakker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private PacketRepository packetRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/packet/{packetid}")
    public ModelAndView orderConfirm(@PathVariable Long packetid, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("order/placeorder");
        modelAndView.addObject("packet", packetRepository.findById(packetid).get());
        CustomUserDetails customUserDetails = (CustomUserDetails) principal;
        User user = userRepository.findByEmail(customUserDetails.getEmail()).get();
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    @PostMapping("/packet/{packetid}")
    public ModelAndView confirmed(@PathVariable Long packetid, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("order/placeorder");
        modelAndView.addObject("packet", packetRepository.findById(packetid).get());
        modelAndView.addObject("user", principal);
        return modelAndView;
    }

}
