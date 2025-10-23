package com.phamvantan.exercise201.service;

import com.phamvantan.exercise201.entity.Notification;
import java.util.List;
import java.util.Optional; // ✅ Import Optional
import java.util.UUID;

public interface NotificationService {
    List<Notification> findAll();
    
    // ✅ SỬA LỖI: Trả về Optional để tương thích với ResponseEntity.of() và JpaRepository
    Optional<Notification> findById(UUID id); 
    
    Notification save(Notification notification);
    void deleteById(UUID id);
    
    List<Notification> getNotificationsForStaff(UUID staffAccountId);
    Notification markAsSeen(UUID notificationId);
}