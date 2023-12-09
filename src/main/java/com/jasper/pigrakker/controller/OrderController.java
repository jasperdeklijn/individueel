package com.jasper.pigrakker.controller;

import com.jasper.pigrakker.repository.PacketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private PacketRepository packetRepository;

    @GetMapping("/packet/{packetid}")
    public ModelAndView orderConfirm(@PathVariable Long packetid, @AuthenticationPrincipal OAuth2User principal) {
        ModelAndView modelAndView = new ModelAndView("order/placeorder");
        modelAndView.addObject("packet", packetRepository.findById(packetid).get());
        modelAndView.addObject("user", principal.getAttribute("name"));
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
