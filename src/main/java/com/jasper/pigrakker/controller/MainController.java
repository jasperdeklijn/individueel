package com.jasper.pigrakker.controller;

import com.jasper.pigrakker.model.Order;
import com.jasper.pigrakker.model.Product;
import com.jasper.pigrakker.model.Status;
import com.jasper.pigrakker.repository.OrderRepository;
import com.jasper.pigrakker.repository.PacketRepository;
import com.jasper.pigrakker.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@Controller
public class MainController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PacketRepository packetRepository;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/")
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView("view/index");
        modelAndView.addObject("products", productRepository.findAll());
        modelAndView.addObject("packets", packetRepository.findAll());
        return modelAndView;
    }
    @GetMapping("/onsdoel")
    public  ModelAndView onsDoel () {
        return  new ModelAndView("view/onsDoel");
    }

    @GetMapping("/login")
    public ModelAndView loginForm()
    {
        return new ModelAndView("view/login");
    }

    @GetMapping("/details")
    @Secured("ROLE_ADMIN")
    public ModelAndView details()
    {
        ModelAndView modelAndView = new ModelAndView("view/details");
        List<Order> orders = orderRepository.findAll();

        int totalOrders = orders.size();
        int reserved = 0;
        int sold = 0;

        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getDelivered()) {
                reserved ++;
            }else {
                sold++;
            }
        }
        List<Product> products = productRepository.findAll();
        Double totalKg = 0.0;
        for (int i = 0; i < products.size(); i++)
        {
            totalKg += products.get(i).getSold();
        }

        Map<String, Integer> graphData = new TreeMap<>();
        graphData.put("Niet Verkocht", totalOrders - reserved - sold);
        graphData.put("Gereserveer", reserved);
        graphData.put("Verkocht", sold);
        modelAndView.addObject("chartData", graphData);
        return new ModelAndView("view/details");
    }
}
