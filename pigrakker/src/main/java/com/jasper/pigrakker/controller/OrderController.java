package com.jasper.pigrakker.controller;

import com.jasper.pigrakker.model.Order;
import com.jasper.pigrakker.model.OrderItem;
import com.jasper.pigrakker.repository.OrderItemsRepository;
import com.jasper.pigrakker.repository.OrderRepository;
import com.jasper.pigrakker.repository.ProductRepository;
import com.jasper.pigrakker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemsRepository orderItemsRepository;
    @Autowired
    ProductRepository productRepository;

    @RequestMapping("/place/{productid}")
    public ModelAndView orderComfirm(@PathVariable Long productid) {
        ModelAndView modelAndView = new ModelAndView("order/placeorder");
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        modelAndView.addObject("orderItem", orderItem);
        modelAndView.addObject("product", productRepository.findById(productid).get());
        return modelAndView;
    }

    @PostMapping("/place/{productid}")
    public ModelAndView orderPlace(OrderItem orderItem, Order order, @PathVariable Long productid) {
        ModelAndView modelAndView = new ModelAndView("order/placeorder");
        order = new Order();
        orderItem = new OrderItem();
        orderItem.setOrder(order);
        modelAndView.addObject("orderItem", orderItem);
        modelAndView.addObject("product", productRepository.findById(productid).get());
        return modelAndView;
    }


}
