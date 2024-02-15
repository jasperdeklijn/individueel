package com.jasper.pigrakker.controller;

import com.jasper.pigrakker.model.Order;
import com.jasper.pigrakker.model.Packet;
import com.jasper.pigrakker.repository.OrderRepository;
import com.jasper.pigrakker.repository.PacketRepository;
import com.jasper.pigrakker.repository.ProductRepository;
import com.nimbusds.jose.shaded.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

        modelAndView.addObject("chartData", getMoneyData());
        modelAndView.addObject("barData",getBarData());
        return modelAndView;
    }

    private Map<String, Object> getBarData() {
        List<Packet> packets = packetRepository.findAll();

        Map<String, Object> graphData = new HashMap<>();
        graphData.put("cols", new String[]{"Pakket", "Verkocht", "Gereserveerd"});
        List<Object[]> rows = new ArrayList<>();
        for (int i = 0; i < packets.size(); i++) {
            List<Order> orderPackets = orderRepository.findAllByPacket(packets.get(i));
            int soldPackets = 0;
            int reservedPackets = 0;
            for(int y = 0; y < orderPackets.size(); y++)
            {
                if (orderPackets.get(y).getDelivered()) {
                    soldPackets ++;
                } else {
                    reservedPackets ++;
                }
            }
            rows.add(new Object[]{
                    packets.get(i).getPacketname(), soldPackets, reservedPackets
            });
        }
        graphData.put("rows",rows);
        Gson gson = new Gson();
        String jsonData = gson.toJson(graphData);
        System.out.println(jsonData); // Print the JSON data to see its content
        return graphData;
    }
    private Map<String, Integer> getMoneyData() {
        List<Order> orders = orderRepository.findAll();
        int totalOrders = orders.size();
        Double CashEarned = 0.0;
        Double CashReserved = 0.0;

        for (int i = 0; i < totalOrders; i++) {
            if (orders.get(i).getDelivered()) {
                CashEarned += orders.get(i).getPacket().getPrice();
            } else {
                CashReserved += orders.get(i).getPacket().getPrice();
            }
        }

        Map<String, Integer> graphData = new HashMap<>();
        graphData.put("Opgehaald", CashEarned.intValue());
        graphData.put("Nog niet", CashReserved.intValue());

        return graphData;
    }
}
