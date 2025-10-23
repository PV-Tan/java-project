package com.phamvantan.exercise201.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.phamvantan.exercise201.entity.Card;
import com.phamvantan.exercise201.service.CardService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    // Lấy danh sách tất cả cards
    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        return ResponseEntity.ok(cardService.findAll());
    }

    // Lấy thông tin card theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable UUID id) {
        return ResponseEntity.ok(cardService.findById(id));
    }

    // Tạo mới card
    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        return ResponseEntity.ok(cardService.create(card));
    }

    // Cập nhật thông tin card
    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable UUID id, @RequestBody Card card) {
        return ResponseEntity.ok(cardService.update(id, card));
    }

    // Xóa card theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable UUID id) {
        cardService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
