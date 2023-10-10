package com.jasper.pigrakker.service;

import com.jasper.pigrakker.model.Order;

import java.util.Collection;

public interface OrderService {
    public abstract void createOrder(Order product);
    public abstract void updateOrder(String id, Order product);
    public abstract void deleteOrder(String id);
    public abstract Collection<Order> getOrders();
}
