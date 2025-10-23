package com.phamvantan.exercise201.service.impl;

import com.phamvantan.exercise201.entity.CardItem;
import com.phamvantan.exercise201.repository.CardItemRepository;
import com.phamvantan.exercise201.service.CardItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CardItemServiceImpl implements CardItemService {

    private final CardItemRepository repository;

    public CardItemServiceImpl(CardItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CardItem> findAll() {
        return repository.findAll();
    }

    @Override
    public CardItem findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("CardItem not found with id: " + id));
    }

    @Override
    public CardItem create(CardItem cardItem) {
        return repository.save(cardItem);
    }

    @Override
    public CardItem update(UUID id, CardItem cardItem) {
        CardItem existing = findById(id);
        if (cardItem.getQuantity() != null) existing.setQuantity(cardItem.getQuantity());
        if (cardItem.getUnitPrice() != null) existing.setUnitPrice(cardItem.getUnitPrice());
        if (cardItem.getCardId() != null) existing.setCardId(cardItem.getCardId());
        if (cardItem.getProductId() != null) existing.setProductId(cardItem.getProductId());
        return repository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) throw new RuntimeException("CardItem not found with id: " + id);
        repository.deleteById(id);
    }
}
