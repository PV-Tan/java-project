package com.phamvantan.exercise201.controller;

import com.phamvantan.exercise201.entity.CardItem;
import com.phamvantan.exercise201.service.CardItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/card-items")
public class CardItemController {

    private final CardItemService service;

    public CardItemController(CardItemService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CardItem>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardItem> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CardItem> create(@RequestBody CardItem cardItem) {
        return ResponseEntity.ok(service.create(cardItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardItem> update(@PathVariable UUID id, @RequestBody CardItem cardItem) {
        return ResponseEntity.ok(service.update(id, cardItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
