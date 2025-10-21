package com.phamvantan.exercise201.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.phamvantan.exercise201.entity.ProductAttributeValue;
import com.phamvantan.exercise201.service.ProductAttributeValueService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product-attribute-values")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductAttributeValueController {

    private final ProductAttributeValueService productAttributeValueService;

    @GetMapping
    public ResponseEntity<List<ProductAttributeValue>> getAll() {
        return ResponseEntity.ok(productAttributeValueService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductAttributeValue> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(productAttributeValueService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ProductAttributeValue> create(@RequestBody ProductAttributeValue pav) {
        return ResponseEntity.ok(productAttributeValueService.create(pav));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductAttributeValue> update(@PathVariable UUID id, @RequestBody ProductAttributeValue pav) {
        return ResponseEntity.ok(productAttributeValueService.update(id, pav));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        productAttributeValueService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
