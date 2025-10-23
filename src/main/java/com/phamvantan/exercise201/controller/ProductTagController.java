package com.phamvantan.exercise201.controller;

import com.phamvantan.exercise201.entity.*;
import com.phamvantan.exercise201.repository.*;
import com.phamvantan.exercise201.service.ProductTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/product-tags")
@RequiredArgsConstructor
public class ProductTagController {

    private final ProductTagService productTagService;
    private final ProductRepository productRepository;
    private final TagRepository tagRepository;
    private final StaffAccountRepository staffAccountRepository;

    // ✅ Lấy tất cả
    @GetMapping
    public List<ProductTag> getAll() {
        return productTagService.findAll();
    }

    // ✅ Lấy theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductTag> getById(@PathVariable UUID id) {
        ProductTag pt = productTagService.findById(id);
        return pt == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(pt);
    }

    // ✅ Tạo mới
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> payload) {
        try {
            ProductTag pt = mapPayload(new ProductTag(), payload, true);
            ProductTag saved = productTagService.save(pt);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // ✅ Cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Map<String, Object> payload) {
        ProductTag existing = productTagService.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();

        try {
            ProductTag updated = mapPayload(existing, payload, false);
            productTagService.save(updated);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // ✅ Xoá
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        productTagService.deleteById(id);
        return ResponseEntity.ok("ProductTag deleted successfully!");
    }

    // 🔧 Helper
    private ProductTag mapPayload(ProductTag pt, Map<String, Object> payload, boolean isCreate) {
        if (payload.get("productId") != null) {
            UUID productId = UUID.fromString(payload.get("productId").toString());
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            pt.setProduct(product);
        }

        if (payload.get("tagId") != null) {
            UUID tagId = UUID.fromString(payload.get("tagId").toString());
            Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new RuntimeException("Tag not found"));
            pt.setTag(tag);
        }

        if (isCreate) pt.setCreatedAt(LocalDateTime.now());
        pt.setUpdatedAt(LocalDateTime.now());

        Map<String, Object> createdByMap = (Map<String, Object>) payload.get("createdBy");
        if (createdByMap != null && createdByMap.get("id") != null) {
            UUID createdById = UUID.fromString(createdByMap.get("id").toString());
            staffAccountRepository.findById(createdById).ifPresent(pt::setCreatedBy);
        }

        Map<String, Object> updatedByMap = (Map<String, Object>) payload.get("updatedBy");
        if (updatedByMap != null && updatedByMap.get("id") != null) {
            UUID updatedById = UUID.fromString(updatedByMap.get("id").toString());
            staffAccountRepository.findById(updatedById).ifPresent(pt::setUpdatedBy);
        }

        return pt;
    }
}
