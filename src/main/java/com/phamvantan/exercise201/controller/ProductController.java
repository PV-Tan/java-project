package com.phamvantan.exercise201.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.phamvantan.exercise201.entity.Product;
import com.phamvantan.exercise201.repository.ProductRepository;
import com.phamvantan.exercise201.repository.StaffAccountRepository;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final StaffAccountRepository staffAccountRepository;

    // ✅ Lấy tất cả
    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    // ✅ Lấy theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable UUID id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Tạo mới
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> payload) {
        try {
            Product product = mapPayload(new Product(), payload, true);
            Product saved = productRepository.save(product);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // ✅ Cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Map<String, Object> payload) {
        return productRepository.findById(id).map(existing -> {
            try {
                Product updated = mapPayload(existing, payload, false);
                productRepository.save(updated);
                return ResponseEntity.ok(updated);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
            }
        }).orElse(ResponseEntity.notFound().build());
    }

    // ✅ Xoá
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById(id);
        return ResponseEntity.ok("Product deleted successfully!");
    }

    // --- Helper: ánh xạ dữ liệu ---
    private Product mapPayload(Product product, Map<String, Object> payload, boolean isCreate) {
        if (payload.get("slug") != null) product.setSlug((String) payload.get("slug"));
        if (payload.get("productName") != null) product.setProductName((String) payload.get("productName"));
        if (payload.get("sku") != null) product.setSku((String) payload.get("sku"));
        if (payload.get("salePrice") != null) product.setSalePrice(new java.math.BigDecimal(payload.get("salePrice").toString()));
        if (payload.get("comparePrice") != null) product.setComparePrice(new java.math.BigDecimal(payload.get("comparePrice").toString()));
        if (payload.get("buyingPrice") != null) product.setBuyingPrice(new java.math.BigDecimal(payload.get("buyingPrice").toString()));
        if (payload.get("quantity") != null) product.setQuantity(Integer.parseInt(payload.get("quantity").toString()));
        if (payload.get("shortDescription") != null) product.setShortDescription((String) payload.get("shortDescription"));
        if (payload.get("productDescription") != null) product.setProductDescription((String) payload.get("productDescription"));
        if (payload.get("productType") != null) product.setProductType((String) payload.get("productType"));
        if (payload.get("published") != null) product.setPublished(Boolean.parseBoolean(payload.get("published").toString()));
        if (payload.get("disableOutOfStock") != null) product.setDisableOutOfStock(Boolean.parseBoolean(payload.get("disableOutOfStock").toString()));
        if (payload.get("note") != null) product.setNote((String) payload.get("note"));

        // createdBy
        if (isCreate) {
            Object createdBy = payload.get("createdBy");
            if (createdBy instanceof Map<?, ?> map && map.get("id") != null) {
                UUID staffId = UUID.fromString(map.get("id").toString());
                product.setCreatedBy(staffAccountRepository.findById(staffId).orElse(null));
            } else {
                product.setCreatedBy(null);
            }
            product.setCreatedAt(LocalDateTime.now());
        }

        // updatedBy
        Object updatedBy = payload.get("updatedBy");
        if (updatedBy instanceof Map<?, ?> map && map.get("id") != null) {
            UUID staffId = UUID.fromString(map.get("id").toString());
            product.setUpdatedBy(staffAccountRepository.findById(staffId).orElse(null));
        } else {
            product.setUpdatedBy(null);
        }
        product.setUpdatedAt(LocalDateTime.now());

        return product;
    }
}
