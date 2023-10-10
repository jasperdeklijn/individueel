package com.jasper.pigrakker.service;

import com.jasper.pigrakker.model.Order;
import com.jasper.pigrakker.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;
    @Override
    public void createOrder(Order order) {

    }

    @Override
    public void updateOrder(String id, Order order) {

    }

    @Override
    public void deleteOrder(String id) {

    }

    @Override
    public Collection<Order> getOrders() {
        return null;
    }
}
