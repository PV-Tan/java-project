package com.phamvantan.exercise201.service;

import com.phamvantan.exercise201.entity.Tag;

import java.util.List;
import java.util.UUID;

public interface TagService {
    List<Tag> findAll();
    Tag findById(UUID id);
    Tag save(Tag tag);
    void deleteById(UUID id);
}
