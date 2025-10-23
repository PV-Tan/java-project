package com.phamvantan.exercise201.controller;

import com.phamvantan.exercise201.entity.StaffAccount;
import com.phamvantan.exercise201.entity.Tag;
import com.phamvantan.exercise201.repository.StaffAccountRepository;
import com.phamvantan.exercise201.repository.TagRepository;
import com.phamvantan.exercise201.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagRepository tagRepository;
    private final StaffAccountRepository staffAccountRepository;

    // ✅ GET ALL
    @GetMapping
    public List<Tag> getAll() {
        return tagService.findAll();
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Tag> getById(@PathVariable UUID id) {
        return ResponseEntity.of(tagRepository.findById(id));
    }

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> payload) {
        String slug = (String) payload.get("slug");
        if (slug != null && tagRepository.existsBySlug(slug)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Slug already exists!"));
        }

        Tag tag = mapPayload(new Tag(), payload, true);
        Tag saved = tagService.save(tag);
        return ResponseEntity.ok(saved);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Map<String, Object> payload) {
        Tag tag = tagService.findById(id);
        if (tag == null) return ResponseEntity.notFound().build();

        String newSlug = (String) payload.get("slug");
        if (newSlug != null) {
            tagRepository.findBySlug(newSlug).ifPresent(existing -> {
                if (!existing.getId().equals(id)) {
                    throw new RuntimeException("Slug already exists!");
                }
            });
        }

        tag = mapPayload(tag, payload, false);
        Tag updated = tagService.save(tag);
        return ResponseEntity.ok(updated);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        tagService.deleteById(id);
        return ResponseEntity.ok("Tag deleted successfully!");
    }

    // 🔧 Helper
    private Tag mapPayload(Tag tag, Map<String, Object> payload, boolean isCreate) {
        tag.setTagName((String) payload.get("tagName"));
        tag.setSlug((String) payload.get("slug"));
        tag.setDescription((String) payload.get("description"));

        if (isCreate) tag.setCreatedAt(LocalDateTime.now());
        tag.setUpdatedAt(LocalDateTime.now());

        // createdBy
        Map<String, Object> createdByMap = (Map<String, Object>) payload.get("createdBy");
        if (createdByMap != null && createdByMap.get("id") instanceof String createdByIdStr) {
            UUID createdById = UUID.fromString(createdByIdStr);
            staffAccountRepository.findById(createdById).ifPresent(tag::setCreatedBy);
        }

        // updatedBy
        Map<String, Object> updatedByMap = (Map<String, Object>) payload.get("updatedBy");
        if (updatedByMap != null && updatedByMap.get("id") instanceof String updatedByIdStr) {
            UUID updatedById = UUID.fromString(updatedByIdStr);
            staffAccountRepository.findById(updatedById).ifPresent(tag::setUpdatedBy);
        }

        return tag;
    }
}
