package com.phamvantan.exercise201.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.phamvantan.exercise201.entity.Notification;
import com.phamvantan.exercise201.entity.StaffAccount;
import com.phamvantan.exercise201.repository.NotificationRepository;
import com.phamvantan.exercise201.repository.StaffAccountRepository; // ✅ Cần giả định StaffAccountRepository tồn tại
import com.phamvantan.exercise201.service.NotificationService;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional; // ✅ Import Optional
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final StaffAccountRepository staffAccountRepository; // ✅ Thêm StaffAccountRepository

    @Override
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    // ✅ SỬA LỖI: Trả về Optional<Notification>
    @Override
    public Optional<Notification> findById(UUID id) {
        return notificationRepository.findById(id);
    }

    @Override
    public Notification save(Notification notification) {
        // ✅ Xử lý Mối quan hệ (rất quan trọng khi nhận Entity trực tiếp qua @RequestBody)
        if (notification.getStaffAccount() != null && notification.getStaffAccount().getId() != null) {
            UUID staffId = notification.getStaffAccount().getId();
            // Tìm StaffAccount từ DB và gán lại cho Notification (Managed Entity)
            StaffAccount managedStaffAccount = staffAccountRepository.findById(staffId).orElseThrow(
                () -> new RuntimeException("StaffAccount with ID " + staffId + " not found.")
            );
            notification.setStaffAccount(managedStaffAccount);
        } else {
            // Xử lý lỗi nếu account_id bị thiếu (tùy theo yêu cầu nghiệp vụ)
            throw new RuntimeException("Staff Account ID must be provided.");
        }
        
        // Logic cập nhật thời gian
        if (notification.getReceiveTime() == null) {
            notification.setReceiveTime(ZonedDateTime.now());
        }
        
        return notificationRepository.save(notification);
    }

    @Override
    public void deleteById(UUID id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public List<Notification> getNotificationsForStaff(UUID staffAccountId) {
        return notificationRepository.findAllByStaffAccountIdOrderByCreatedAtDesc(staffAccountId);
    }

    @Override
    public Notification markAsSeen(UUID notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElse(null);
        if (notification != null) {
            notification.setSeen(true);
            return notificationRepository.save(notification);
        }
        return null;
    }
}