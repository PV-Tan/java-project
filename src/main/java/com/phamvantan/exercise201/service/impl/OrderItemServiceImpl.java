package com.phamvantan.exercise201.service.impl;

import com.phamvantan.exercise201.entity.Order;
import com.phamvantan.exercise201.entity.OrderItem;
import com.phamvantan.exercise201.entity.Product;
import com.phamvantan.exercise201.repository.OrderItemRepository;
import com.phamvantan.exercise201.repository.OrderRepository;
import com.phamvantan.exercise201.repository.ProductRepository;
import com.phamvantan.exercise201.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Override
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public Optional<OrderItem> findById(UUID id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public OrderItem create(Map<String, Object> payload) {
        // Lấy và kiểm tra productId
        String productIdStr = (String) payload.get("productId");
        if (productIdStr == null) {
            throw new IllegalArgumentException("productId is required.");
        }
        UUID productId = UUID.fromString(productIdStr);

        // Lấy và kiểm tra orderId
        String orderId = (String) payload.get("orderId");
        if (orderId == null) {
            throw new IllegalArgumentException("orderId is required.");
        }

        // Tìm Product và Order, nếu không có sẽ báo lỗi
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        
        // Lấy price và quantity từ payload
        BigDecimal price = new BigDecimal(payload.get("price").toString());
        Integer quantity = (Integer) payload.get("quantity");
        
        // Tạo đối tượng OrderItem mới
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setOrder(order);
        orderItem.setPrice(price);
        orderItem.setQuantity(quantity);

        return orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteById(UUID id) {
        if (!orderItemRepository.existsById(id)) {
            throw new RuntimeException("OrderItem not found with id: " + id);
        }
        orderItemRepository.deleteById(id);
    }
}