package com.phamvantan.exercise201.entity; // Adjust package as needed

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_statuses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderStatus {

    // id: UUID NOT NULL DEFAULT uuid_generate, auto-generated as requested [cite: 75, 76, 77]
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // status_name: VARCHAR(255) NOT NULL
    @Column(name = "status_name", nullable = false, length = 255)
    private String statusName;

    // color: VARCHAR(50) NOT NULL
    @Column(name = "color", nullable = false, length = 50)
    private String color;

    // privacy: VARCHAR(10) CHECK (privacy IN ('public'))
    @Column(name = "privacy", nullable = false, length = 10)
    private String privacy;

    // created_at: TIMESTAMPTZ NOT NULL DEFAULT NOW()
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    // updated_at: TIMESTAMPTZ NOT NULL DEFAULT NOW()
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedAt;

    // created_by: UUID REFERENCES staff_accounts(id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private StaffAccount createdBy;

    // updated_by: UUID REFERENCES staff_accounts(id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private StaffAccount updatedBy;

    // Automatically set createdAt and updatedAt before persisting
    @PrePersist
    protected void onCreate() {
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    // Automatically update updatedAt before updating
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }
}
// Note: Assumes StaffAccount class is available in the same package (com.ngodinhhung.exercise201.entity) as per the provided context[cite: 79].