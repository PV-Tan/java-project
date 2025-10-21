package com.phamvantan.exercise201.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.phamvantan.exercise201.entity.Category;
import com.phamvantan.exercise201.repository.CategoryRepository;
import com.phamvantan.exercise201.repository.StaffAccountRepository;
import com.phamvantan.exercise201.service.CategoryService;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;
    private final CategoryRepository categoryRepository;
    private final StaffAccountRepository staffAccountRepository;

    // ✅ READ ALL
    @GetMapping
    public List<Category> getAll() {
        return service.findAll();
    }

    // ✅ READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Map<String, Object> payload) {
        Category category = new Category();
        category.setCategoryName((String) payload.get("categoryName"));
        category.setCategoryDescription((String) payload.get("categoryDescription"));
        category.setIcon((String) payload.get("icon"));
        category.setImage((String) payload.get("image"));
        category.setPlaceholder((String) payload.get("placeholder"));
        category.setActive((Boolean) payload.getOrDefault("active", true));
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());

        // Gán parent (nếu có)
        if (payload.get("parent") instanceof Map<?, ?> parentMap) {
            if (parentMap.get("id") instanceof String parentIdStr) {
                UUID parentId = UUID.fromString(parentIdStr);
                categoryRepository.findById(parentId).ifPresent(category::setParent);
            }
        }

        // createdBy
        if (payload.get("createdBy") instanceof Map<?, ?> createdByMap) {
            if (createdByMap.get("id") instanceof String createdByIdStr) {
                UUID createdById = UUID.fromString(createdByIdStr);
                staffAccountRepository.findById(createdById).ifPresent(category::setCreatedBy);
            }
        }

        // updatedBy
        if (payload.get("updatedBy") instanceof Map<?, ?> updatedByMap) {
            if (updatedByMap.get("id") instanceof String updatedByIdStr) {
                UUID updatedById = UUID.fromString(updatedByIdStr);
                staffAccountRepository.findById(updatedById).ifPresent(category::setUpdatedBy);
            }
        }

        return ResponseEntity.ok(service.save(category));
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable UUID id, @RequestBody Map<String, Object> payload) {
        Category category = service.findById(id);
        category.setCategoryName((String) payload.get("categoryName"));
        category.setCategoryDescription((String) payload.get("categoryDescription"));
        category.setIcon((String) payload.get("icon"));
        category.setImage((String) payload.get("image"));
        category.setPlaceholder((String) payload.get("placeholder"));
        category.setActive((Boolean) payload.getOrDefault("active", true));
        category.setUpdatedAt(LocalDateTime.now());

        // parent
        if (payload.get("parent") instanceof Map<?, ?> parentMap) {
            if (parentMap.get("id") instanceof String parentIdStr) {
                UUID parentId = UUID.fromString(parentIdStr);
                categoryRepository.findById(parentId).ifPresent(category::setParent);
            }
        }

        // updatedBy
        if (payload.get("updatedBy") instanceof Map<?, ?> updatedByMap) {
            if (updatedByMap.get("id") instanceof String updatedByIdStr) {
                UUID updatedById = UUID.fromString(updatedByIdStr);
                staffAccountRepository.findById(updatedById).ifPresent(category::setUpdatedBy);
            }
        }

        return ResponseEntity.ok(service.save(category));
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.ok("Category deleted successfully!");
    }
}
