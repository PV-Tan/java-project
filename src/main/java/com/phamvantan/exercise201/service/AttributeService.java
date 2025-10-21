package com.phamvantan.exercise201.service;

import java.util.List;
import java.util.UUID;

import com.phamvantan.exercise201.entity.Attribute;

public interface AttributeService {
    List<Attribute> getAll();
    Attribute getById(UUID id);
    Attribute create(Attribute attribute);
    Attribute update(UUID id, Attribute attribute);
    void delete(UUID id);
}
