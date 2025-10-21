package com.phamvantan.exercise201.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.phamvantan.exercise201.entity.Order;
import com.phamvantan.exercise201.repository.OrderRepository;
import com.phamvantan.exercise201.service.OrderService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(String id, Order order) {
        return orderRepository.findById(id)
                .map(existing -> {
                    existing.setCoupon(order.getCoupon());
                    existing.setCustomer(order.getCustomer());
                    existing.setOrderStatus(order.getOrderStatus());
                    existing.setOrderApprovedAt(order.getOrderApprovedAt());
                    existing.setOrderDeliveredCarrierDate(order.getOrderDeliveredCarrierDate());
                    existing.setOrderDeliveredCustomerDate(order.getOrderDeliveredCustomerDate());
                    existing.setUpdatedBy(order.getUpdatedBy());
                    return orderRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }
}
