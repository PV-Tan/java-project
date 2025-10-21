package com.phamvantan.exercise201.service;

import com.phamvantan.exercise201.entity.Sell;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface SellService {
    List<Sell> findAll();
    Optional<Sell> findById(UUID id);
    Sell create(Map<String, Object> payload);
    Sell update(UUID id, Map<String, Object> payload);
    void deleteById(UUID id);
}