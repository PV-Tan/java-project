package com.phamvantan.exercise201.service.impl;

import com.phamvantan.exercise201.entity.Tag;
import com.phamvantan.exercise201.repository.TagRepository;
import com.phamvantan.exercise201.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag findById(UUID id) {
        return tagRepository.findById(id).orElse(null);
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void deleteById(UUID id) {
        tagRepository.deleteById(id);
    }
}
