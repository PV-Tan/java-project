package com.phamvantan.exercise201.service; // Adjust package as needed

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.phamvantan.exercise201.entity.OrderStatus;

public interface OrderStatusService {

    OrderStatus save(OrderStatus orderStatus);

    Optional<OrderStatus> findById(UUID id);

    List<OrderStatus> findAll();

    void deleteById(UUID id);
}