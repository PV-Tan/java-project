package com.phamvantan.exercise201.repository; // Adjust package as needed

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phamvantan.exercise201.entity.OrderStatus;

import java.util.UUID;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, UUID> {
    // Custom query methods can be added here if needed, 
    // e.g., OrderStatus findByStatusName(String statusName);
}