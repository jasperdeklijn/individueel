package com.jasper.pigrakker.controller;

import com.jasper.pigrakker.repository.PacketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PacketController {

    @Autowired
    private PacketRepository packetRepository;

    @GetMapping("admin/packet")
    public ModelAndView allProducts() {
        ModelAndView modelAndView = new ModelAndView("admin/packets");
        modelAndView.addObject("packets", packetRepository.findAll());
        return modelAndView;
    }

}
