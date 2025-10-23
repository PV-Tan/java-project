package com.phamvantan.exercise201.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.phamvantan.exercise201.entity.Card;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {
    // Không cần thêm method đặc biệt nào cho yêu cầu hiện tại
}
