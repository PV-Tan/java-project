package com.phamvantan.exercise201.service;

import com.phamvantan.exercise201.entity.CardItem;
import java.util.List;
import java.util.UUID;

public interface CardItemService {
    List<CardItem> findAll();
    CardItem findById(UUID id);
    CardItem create(CardItem cardItem);
    CardItem update(UUID id, CardItem cardItem);
    void delete(UUID id);
}
