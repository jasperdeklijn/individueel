package com.jasper.pigrakker.controller;

import com.jasper.pigrakker.model.Order;
import com.jasper.pigrakker.model.Packet;
import com.jasper.pigrakker.model.Product;
import com.jasper.pigrakker.model.Status;
import com.jasper.pigrakker.repository.OrderRepository;
import com.jasper.pigrakker.repository.PacketRepository;
import com.jasper.pigrakker.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private PacketRepository packetRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/admin")
    @Secured("ROLE_ADMIN")
    public ModelAndView allProducts() {
        ModelAndView modelAndView = new ModelAndView("admin/orders");
        modelAndView.addObject("orders", orderRepository.findAll());
        return modelAndView;
    }
    @GetMapping("/packet/{packetid}")
    public ModelAndView orderConfirm(@PathVariable Long packetid, @AuthenticationPrincipal OAuth2User principal) {
        ModelAndView modelAndView = new ModelAndView("order/placeorder");
        Optional<Packet> packet =  packetRepository.findById(packetid);
        if(packet.isEmpty()){
            throw new IllegalArgumentException();
        }
        modelAndView.addObject("packet", packet.get());
        Order order = new Order();
        order.setPacket(packet.get());
        if (principal != null) {
            modelAndView.addObject("user", principal.getAttribute("name"));
            order.setName(principal.getAttribute("email"));

        }
        modelAndView.addObject("order", order);
        return modelAndView;
    }
    @PostMapping("/packet/{packetid}")
    public ModelAndView confirmed(@PathVariable Long packetid,  @Validated Order order, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        Optional<Packet> packet =  packetRepository.findById(packetid);
        if(packet.isEmpty()){
            throw new IllegalArgumentException();
        }
        order.setPacket(packet.get());
        order.setStatus(Status.NEEDSPAYREQUEST);
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("alertMessage", "Er klopt iets niet :(");
            modelAndView.setViewName("order/placeorder");
            return modelAndView;
        }
        Product product = productRepository.findFirstProductWithLowestPercentageSold().get();
        product.setSold((product.getSold() + packet.get().getTotalKG()));

        orderRepository.save(order);
        productRepository.save(product);
        modelAndView.addObject("alertMessage", "Bedankt voor uw bestelling");
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

}
