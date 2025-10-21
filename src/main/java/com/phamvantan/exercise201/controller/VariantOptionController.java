package com.phamvantan.exercise201.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.phamvantan.exercise201.entity.VariantOption;
import com.phamvantan.exercise201.service.VariantOptionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/variant-options")
public class VariantOptionController {

    private final VariantOptionService variantOptionService;

    public VariantOptionController(VariantOptionService variantOptionService) {
        this.variantOptionService = variantOptionService;
    }

    // GET /api/variant-options
    @GetMapping
    public ResponseEntity<List<VariantOption>> getAllVariantOptions() {
        List<VariantOption> options = variantOptionService.getAllVariantOptions();
        return ResponseEntity.ok(options);
    }

    // GET /api/variant-options/{id}
    @GetMapping("/{id}")
    public ResponseEntity<VariantOption> getVariantOptionById(@PathVariable UUID id) {
        VariantOption option = variantOptionService.getVariantOptionById(id);
        return ResponseEntity.ok(option);
    }

    // POST /api/variant-options
    @PostMapping
    public ResponseEntity<VariantOption> createVariantOption(@RequestBody VariantOption variantOption) {
        VariantOption createdOption = variantOptionService.createVariantOption(variantOption);
        return new ResponseEntity<>(createdOption, HttpStatus.CREATED);
    }

    // PUT /api/variant-options/{id}
    @PutMapping("/{id}")
    public ResponseEntity<VariantOption> updateVariantOption(@PathVariable UUID id, @RequestBody VariantOption variantOptionDetails) {
        VariantOption updatedOption = variantOptionService.updateVariantOption(id, variantOptionDetails);
        return ResponseEntity.ok(updatedOption);
    }

    // DELETE /api/variant-options/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVariantOption(@PathVariable UUID id) {
        variantOptionService.deleteVariantOption(id);
        return ResponseEntity.noContent().build();
    }
}