package com.phamvantan.exercise201.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // ✅ thêm dòng này
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers")
@EntityListeners(AuditingEntityListener.class)
// ✅ Bỏ qua các proxy của Hibernate khi chuyển sang JSON (fix lỗi ByteBuddyInterceptor)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Tự sinh UUID cho ID
    private UUID id; // UUID NOT NULL DEFAULT uuid_generate_v4()

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName; // VARCHAR(100) NOT NULL

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName; // VARCHAR(100) NOT NULL

    @Column(name = "email", columnDefinition = "TEXT", nullable = false, unique = true)
    private String email; // TEXT NOT NULL UNIQUE

    @Column(name = "password_hash", columnDefinition = "TEXT", nullable = false)
    private String passwordHash; // TEXT NOT NULL

    @Column(name = "active", nullable = false)
    private Boolean active = true; // BOOLEAN DEFAULT TRUE

    @CreatedDate
    @Column(name = "registered_at", nullable = false, updatable = false)
    private OffsetDateTime registeredAt = OffsetDateTime.now(); // Gán mặc định tránh null

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt = OffsetDateTime.now(); // Gán mặc định tránh null
}
