package com.phamvantan.exercise201.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.phamvantan.exercise201.entity.AttributeValue;
import com.phamvantan.exercise201.service.AttributeValueService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/attribute-values")
public class AttributeValueController {

    private final AttributeValueService attributeValueService;

    public AttributeValueController(AttributeValueService attributeValueService) {
        this.attributeValueService = attributeValueService;
    }

    // GET /api/attribute-values
    // Endpoint liệt kê thông tin đầy đủ của AttributeValue, Attribute và StaffAccount liên quan
    @GetMapping
    public ResponseEntity<List<AttributeValue>> getAllAttributeValuesWithDetails() {
        List<AttributeValue> attributeValues = attributeValueService.findAllWithDetails();
        return ResponseEntity.ok(attributeValues);
    }

    // GET /api/attribute-values/{id}
    @GetMapping("/{id}")
    public ResponseEntity<AttributeValue> getAttributeValueById(@PathVariable UUID id) {
        return attributeValueService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/attribute-values
    @PostMapping
    public AttributeValue createAttributeValue(@RequestBody AttributeValue attributeValue) {
        return attributeValueService.save(attributeValue);
    }

    // PUT /api/attribute-values/{id}
    @PutMapping("/{id}")
    public ResponseEntity<AttributeValue> updateAttributeValue(@PathVariable UUID id, @RequestBody AttributeValue attributeValueDetails) {
        return attributeValueService.findById(id)
                .map(existingValue -> {
                    // Cập nhật các trường
                    existingValue.setAttribute(attributeValueDetails.getAttribute()); 
                    existingValue.setAttributeValue(attributeValueDetails.getAttributeValue());
                    existingValue.setColor(attributeValueDetails.getColor());
                    AttributeValue updatedValue = attributeValueService.save(existingValue);
                    return ResponseEntity.ok(updatedValue);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/attribute-values/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttributeValue(@PathVariable UUID id) {
        if (attributeValueService.findById(id).isPresent()) {
            attributeValueService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}