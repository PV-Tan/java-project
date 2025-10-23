package com.phamvantan.exercise201.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // UUID NOT NULL DEFAULT
    private UUID id;

    // UUID REFERENCES staff_accounts(id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    // Giả sử StaffAccount đã có @JsonIgnoreProperties để tránh lặp vô hạn
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private StaffAccount staffAccount;

    @Column(name = "title", length = 100) // VARCHAR(100)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT") // TEXT
    private String content;

    @Column(name = "seen") // BOOLEAN
    private Boolean seen = false; // Mặc định là FALSE (chưa xem)

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMPTZ DEFAULT NOW()") // TIMESTAMPTZ NOT NULL DEFAULT NOW()
    private ZonedDateTime createdAt = ZonedDateTime.now();

    @Column(name = "receive_time", columnDefinition = "TIMESTAMPTZ") // TIMESTAMPTZ
    private ZonedDateTime receiveTime;

    @Column(name = "notification_expiry_date", columnDefinition = "DATE") // DATE
    private Date notificationExpiryDate;
    
    // Lưu ý: Dùng ZonedDateTime cho TIMESTAMPTZ để xử lý múi giờ. 
    // Dùng java.util.Date hoặc java.time.LocalDate cho DATE.
    // Tôi dùng ZonedDateTime và Date cho phù hợp nhất với các kiểu dữ liệu của PostgreSQL.
}