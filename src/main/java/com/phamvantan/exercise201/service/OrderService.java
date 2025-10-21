package com.phamvantan.exercise201.service;

import java.util.List;
import java.util.Optional;

import com.phamvantan.exercise201.entity.Order;

public interface OrderService {
    List<Order> getAllOrders();
    Optional<Order> getOrderById(String id);
    Order createOrder(Order order);
    Order updateOrder(String id, Order order);
    void deleteOrder(String id);
}
