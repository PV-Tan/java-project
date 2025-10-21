package com.phamvantan.exercise201.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.phamvantan.exercise201.entity.ProductShippingInfo;
import com.phamvantan.exercise201.service.ProductShippingInfoService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product-shipping-info")
@RequiredArgsConstructor
public class ProductShippingInfoController {

    private final ProductShippingInfoService service;

    @GetMapping
    public ResponseEntity<List<ProductShippingInfo>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductShippingInfo> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductShippingInfo> getByProduct(@PathVariable UUID productId) {
        return ResponseEntity.ok(service.findByProductId(productId));
    }

    @PostMapping
    public ResponseEntity<ProductShippingInfo> create(@RequestBody ProductShippingInfo shippingInfo) {
        return ResponseEntity.ok(service.save(shippingInfo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductShippingInfo> update(@PathVariable UUID id,
                                                      @RequestBody ProductShippingInfo shippingInfo) {
        shippingInfo.setId(id);
        return ResponseEntity.ok(service.save(shippingInfo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
