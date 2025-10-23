package com.phamvantan.exercise201.service;

import java.util.List;
import java.util.UUID;
import com.phamvantan.exercise201.entity.Card;

public interface CardService {
    List<Card> findAll();
    Card findById(UUID id);
    Card create(Card card);
    Card update(UUID id, Card card);
    void delete(UUID id);
}
