package com.phamvantan.exercise201.service.impl;

import com.phamvantan.exercise201.entity.Product;
import com.phamvantan.exercise201.entity.Sell;
import com.phamvantan.exercise201.repository.ProductRepository;
import com.phamvantan.exercise201.repository.SellRepository;
import com.phamvantan.exercise201.service.SellService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SellServiceImpl implements SellService {

    private final SellRepository sellRepository;
    private final ProductRepository productRepository; // Để lấy thông tin Product

    @Override
    public List<Sell> findAll() {
        return sellRepository.findAll();
    }

    @Override
    public Optional<Sell> findById(UUID id) {
        return sellRepository.findById(id);
    }

    @Override
    @Transactional
    public Sell create(Map<String, Object> payload) {
        Sell sell = new Sell();
        mapPayloadToSell(sell, payload);
        return sellRepository.save(sell);
    }

    @Override
    @Transactional
    public Sell update(UUID id, Map<String, Object> payload) {
        Sell existingSell = sellRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sell not found with id: " + id));
        mapPayloadToSell(existingSell, payload);
        return sellRepository.save(existingSell);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        if (!sellRepository.existsById(id)) {
            throw new RuntimeException("Sell not found with id: " + id);
        }
        sellRepository.deleteById(id);
    }

    // Helper function để ánh xạ payload vào entity
    private void mapPayloadToSell(Sell sell, Map<String, Object> payload) {
        // Lấy và kiểm tra product_id
        Object productIdObj = payload.get("product_id");
        if (productIdObj == null) {
            throw new IllegalArgumentException("product_id is required.");
        }

        UUID productId = UUID.fromString(productIdObj.toString());
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        sell.setProduct(product);

        // Lấy giá và số lượng
        Object priceObj = payload.get("price");
        if (priceObj == null) {
            throw new IllegalArgumentException("Price is required.");
        }
        sell.setPrice(new BigDecimal(priceObj.toString()));

        Object quantityObj = payload.get("quantity");
        if (quantityObj == null) {
            throw new IllegalArgumentException("Quantity is required.");
        }
        sell.setQuantity(Integer.parseInt(quantityObj.toString()));
    }
}