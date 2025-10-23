package com.phamvantan.exercise201.repository;

import com.phamvantan.exercise201.entity.Sideshow;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SideshowRepository extends JpaRepository<Sideshow, UUID> {
    // Không cần thêm phương thức đặc biệt
}