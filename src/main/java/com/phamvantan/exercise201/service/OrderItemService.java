package com.phamvantan.exercise201.service;

import com.phamvantan.exercise201.entity.OrderItem;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface OrderItemService {
    List<OrderItem> findAll();
    Optional<OrderItem> findById(UUID id);
    OrderItem create(Map<String, Object> payload); // Thay đổi ở đây
    void deleteById(UUID id);
}