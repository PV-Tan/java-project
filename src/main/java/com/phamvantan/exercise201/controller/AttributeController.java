package com.phamvantan.exercise201.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.phamvantan.exercise201.entity.Attribute;
import com.phamvantan.exercise201.service.AttributeService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/attributes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Cho phép frontend/Postman gọi API
public class AttributeController {

    private final AttributeService attributeService;

    // Lấy danh sách tất cả attributes
    @GetMapping
    public ResponseEntity<List<Attribute>> getAll() {
        return ResponseEntity.ok(attributeService.getAll());
    }

    // Lấy attribute theo id
    @GetMapping("/{id}")
    public ResponseEntity<Attribute> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(attributeService.getById(id));
    }

    // Tạo mới attribute
    @PostMapping
    public ResponseEntity<Attribute> create(@RequestBody Attribute attribute) {
        attribute.setCreatedAt(LocalDateTime.now());
        attribute.setUpdatedAt(LocalDateTime.now());
        Attribute created = attributeService.create(attribute);
        return ResponseEntity.ok(created);
    }

    // Cập nhật attribute theo id
    @PutMapping("/{id}")
    public ResponseEntity<Attribute> update(@PathVariable UUID id, @RequestBody Attribute attribute) {
        attribute.setUpdatedAt(LocalDateTime.now());
        Attribute updated = attributeService.update(id, attribute);
        return ResponseEntity.ok(updated);
    }

    // Xóa attribute theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        attributeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
