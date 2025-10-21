package com.phamvantan.exercise201.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.phamvantan.exercise201.entity.Variant;
import com.phamvantan.exercise201.service.VariantService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/variants")
public class VariantController {

    private final VariantService variantService;

    public VariantController(VariantService variantService) {
        this.variantService = variantService;
    }

    // GET /api/variants
    @GetMapping
    public ResponseEntity<List<Variant>> getAllVariants() {
        List<Variant> variants = variantService.getAllVariants();
        return ResponseEntity.ok(variants);
    }

    // GET /api/variants/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Variant> getVariantById(@PathVariable UUID id) {
        Variant variant = variantService.getVariantById(id);
        return ResponseEntity.ok(variant);
    }

    // POST /api/variants
    @PostMapping
    public ResponseEntity<Variant> createVariant(@RequestBody Variant variant) {
        Variant createdVariant = variantService.createVariant(variant);
        return new ResponseEntity<>(createdVariant, HttpStatus.CREATED);
    }

    // PUT /api/variants/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Variant> updateVariant(@PathVariable UUID id, @RequestBody Variant variantDetails) {
        Variant updatedVariant = variantService.updateVariant(id, variantDetails);
        return ResponseEntity.ok(updatedVariant);
    }

    // DELETE /api/variants/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVariant(@PathVariable UUID id) {
        variantService.deleteVariant(id);
        return ResponseEntity.noContent().build();
    }
}