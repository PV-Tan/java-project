package com.phamvantan.exercise201.controller; // Adjust package as needed

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.phamvantan.exercise201.entity.OrderStatus;
import com.phamvantan.exercise201.service.OrderStatusService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/order-statuses") // Base URI for this resource
@RequiredArgsConstructor
public class OrderStatusController {

    private final OrderStatusService orderStatusService;

    // GET all order statuses
    @GetMapping
    public ResponseEntity<List<OrderStatus>> getAllOrderStatuses() {
        List<OrderStatus> statuses = orderStatusService.findAll();
        return ResponseEntity.ok(statuses);
    }

    // GET an order status by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderStatus> getOrderStatusById(@PathVariable UUID id) {
        return orderStatusService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST create a new order status
    @PostMapping
    public ResponseEntity<OrderStatus> createOrderStatus(@RequestBody OrderStatus orderStatus) {
        OrderStatus savedStatus = orderStatusService.save(orderStatus);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStatus);
    }

    // PUT update an existing order status
    @PutMapping("/{id}")
    public ResponseEntity<OrderStatus> updateOrderStatus(@PathVariable UUID id, @RequestBody OrderStatus statusDetails) {
        return orderStatusService.findById(id)
                .map(existingStatus -> {
                    // Update fields
                    existingStatus.setStatusName(statusDetails.getStatusName());
                    existingStatus.setColor(statusDetails.getColor());
                    existingStatus.setPrivacy(statusDetails.getPrivacy());
                    // updated_at is handled by @PreUpdate in the Entity
                    existingStatus.setUpdatedBy(statusDetails.getUpdatedBy()); 
                    
                    OrderStatus updatedStatus = orderStatusService.save(existingStatus);
                    return ResponseEntity.ok(updatedStatus);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE an order status
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderStatus(@PathVariable UUID id) {
        if (orderStatusService.findById(id).isPresent()) {
            orderStatusService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}