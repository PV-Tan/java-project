package com.phamvantan.exercise201.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.phamvantan.exercise201.entity.Notification;
import com.phamvantan.exercise201.service.NotificationService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    // ✅ READ ALL
    @GetMapping
    public List<Notification> getAll() {
        return notificationService.findAll();
    }

    // ✅ READ BY ID (Sẽ hoạt động đúng sau khi sửa Service trả về Optional)
    @GetMapping("/{id}")
    public ResponseEntity<Notification> getById(@PathVariable UUID id) {
        // Nếu findById() trả về Optional<Notification>, dòng này sẽ hoạt động
        return ResponseEntity.of(notificationService.findById(id)); 
    }

    // ✅ CREATE (Sẽ hoạt động đúng sau khi thêm logic xử lý StaffAccount trong Service)
    @PostMapping
    public ResponseEntity<Notification> create(@RequestBody Notification notification) {
        Notification saved = notificationService.save(notification);
        return ResponseEntity.ok(saved);
    }

    // ✅ UPDATE (PatchMapping)
    @PatchMapping("/{id}")
    public ResponseEntity<Notification> updateSeen(@PathVariable UUID id, @RequestBody Map<String, Object> payload) {
        Notification notification = notificationService.findById(id).orElse(null);
        if (notification == null) return ResponseEntity.notFound().build();
        
        // Cập nhật trường 'seen'
        if (payload.containsKey("seen") && payload.get("seen") instanceof Boolean seenValue) {
            notification.setSeen(seenValue);
        }
        
        Notification updated = notificationService.save(notification);
        return ResponseEntity.ok(updated);
    }
    
    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        notificationService.deleteById(id);
        return ResponseEntity.ok("Notification successfully deleted!");
    }

    // API để lấy thông báo theo Staff Account
    @GetMapping("/staff/{staffAccountId}")
    public List<Notification> getNotificationsByStaff(@PathVariable UUID staffAccountId) {
        return notificationService.getNotificationsForStaff(staffAccountId);
    }
}