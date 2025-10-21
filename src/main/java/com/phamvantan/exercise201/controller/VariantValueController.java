package com.phamvantan.exercise201.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.phamvantan.exercise201.entity.VariantValue;
import com.phamvantan.exercise201.service.VariantValueService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/variant-values")
public class VariantValueController {

    private final VariantValueService variantValueService;

    public VariantValueController(VariantValueService variantValueService) {
        this.variantValueService = variantValueService;
    }

    // GET /api/variant-values
    @GetMapping
    public ResponseEntity<List<VariantValue>> getAllVariantValues() {
        List<VariantValue> variantValues = variantValueService.getAllVariantValues();
        return ResponseEntity.ok(variantValues);
    }

    // GET /api/variant-values/{id}
    @GetMapping("/{id}")
    public ResponseEntity<VariantValue> getVariantValueById(@PathVariable UUID id) {
        VariantValue variantValue = variantValueService.getVariantValueById(id);
        return ResponseEntity.ok(variantValue);
    }

    // POST /api/variant-values
    @PostMapping
    public ResponseEntity<VariantValue> createVariantValue(@RequestBody VariantValue variantValue) {
        VariantValue createdVariantValue = variantValueService.createVariantValue(variantValue);
        return new ResponseEntity<>(createdVariantValue, HttpStatus.CREATED);
    }

    // PUT /api/variant-values/{id}
    @PutMapping("/{id}")
    public ResponseEntity<VariantValue> updateVariantValue(@PathVariable UUID id, @RequestBody VariantValue variantValueDetails) {
        VariantValue updatedVariantValue = variantValueService.updateVariantValue(id, variantValueDetails);
        return ResponseEntity.ok(updatedVariantValue);
    }

    // DELETE /api/variant-values/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVariantValue(@PathVariable UUID id) {
        variantValueService.deleteVariantValue(id);
        return ResponseEntity.noContent().build();
    }
}