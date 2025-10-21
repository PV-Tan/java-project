package com.phamvantan.exercise201.controller;

import com.phamvantan.exercise201.entity.Sell;
import com.phamvantan.exercise201.service.SellService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/sells")
@RequiredArgsConstructor
public class SellController {

    private final SellService sellService;

    // Lấy tất cả
    @GetMapping
    public List<Sell> getAll() {
        return sellService.findAll();
    }

    // Lấy theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Sell> getById(@PathVariable UUID id) {
        return sellService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Tạo mới
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> payload) {
        try {
            Sell savedSell = sellService.create(payload);
            return new ResponseEntity<>(savedSell, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Map<String, Object> payload) {
        try {
            Sell updatedSell = sellService.update(id, payload);
            return ResponseEntity.ok(updatedSell);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Xóa
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        try {
            sellService.deleteById(id);
            return ResponseEntity.ok("Sell deleted successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}