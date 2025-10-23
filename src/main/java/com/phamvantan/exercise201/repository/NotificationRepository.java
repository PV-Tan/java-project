package com.phamvantan.exercise201.repository;

import com.phamvantan.exercise201.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    // Thêm các phương thức tùy chỉnh nếu cần, ví dụ:
    List<Notification> findAllByStaffAccountIdOrderByCreatedAtDesc(UUID staffAccountId);
    List<Notification> findByStaffAccountIdAndSeen(UUID staffAccountId, Boolean seen);
}