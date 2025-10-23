package com.phamvantan.exercise201.repository;

import com.phamvantan.exercise201.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {
    Optional<Tag> findBySlug(String slug);
    boolean existsBySlug(String slug);
}
