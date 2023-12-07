package com.jasper.pigrakker.controller;

import com.jasper.pigrakker.repository.PacketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private PacketRepository packetRepository;

    @GetMapping("/packet/{packetid}")
    public ModelAndView orderConfirm(@PathVariable Long packetid, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("order/placeorder");
        modelAndView.addObject("packet", packetRepository.findById(packetid));
        modelAndView.addObject("user", principal);
        return modelAndView;
    }

}
