package com.phamvantan.exercise201.service.impl; // Adjust package as needed

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phamvantan.exercise201.entity.OrderStatus;
import com.phamvantan.exercise201.repository.OrderStatusRepository;
import com.phamvantan.exercise201.service.OrderStatusService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderStatusServiceImpl implements OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;

    @Override
    @Transactional
    public OrderStatus save(OrderStatus orderStatus) {
        // Business logic or validation (e.g., check if staff account IDs exist) goes here
        return orderStatusRepository.save(orderStatus);
    }

    @Override
    public Optional<OrderStatus> findById(UUID id) {
        return orderStatusRepository.findById(id);
    }

    @Override
    public List<OrderStatus> findAll() {
        return orderStatusRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        orderStatusRepository.deleteById(id);
    }
}