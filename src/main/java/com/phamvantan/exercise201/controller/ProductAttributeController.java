package com.phamvantan.exercise201.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.phamvantan.exercise201.entity.*;
import com.phamvantan.exercise201.repository.AttributeRepository;
import com.phamvantan.exercise201.repository.ProductRepository;
import com.phamvantan.exercise201.service.ProductAttributeService;

import java.util.*;

@RestController
@RequestMapping("/api/product-attributes")
@RequiredArgsConstructor
public class ProductAttributeController {

    private final ProductAttributeService productAttributeService;
    private final ProductRepository productRepository;
    private final AttributeRepository attributeRepository;

    // ✅ Lấy tất cả
    @GetMapping
    public List<ProductAttribute> getAll() {
        return productAttributeService.findAll();
    }

    // ✅ Lấy theo id
    @GetMapping("/{id}")
    public ResponseEntity<ProductAttribute> getById(@PathVariable UUID id) {
        ProductAttribute pa = productAttributeService.findById(id);
        return (pa != null) ? ResponseEntity.ok(pa) : ResponseEntity.notFound().build();
    }

    // ✅ Tạo mới
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> payload) {
        Map<String, Object> productMap = (Map<String, Object>) payload.get("product");
        Map<String, Object> attributeMap = (Map<String, Object>) payload.get("attribute");

        if (productMap == null || attributeMap == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Missing product or attribute field"));
        }

        UUID productId = UUID.fromString(productMap.get("id").toString());
        UUID attributeId = UUID.fromString(attributeMap.get("id").toString());

        ProductAttribute pa = new ProductAttribute();

        productRepository.findById(productId).ifPresent(pa::setProduct);
        attributeRepository.findById(attributeId).ifPresent(pa::setAttribute);

        ProductAttribute saved = productAttributeService.save(pa);
        return ResponseEntity.ok(saved);
    }

    // ✅ Xóa theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        productAttributeService.deleteById(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
