package com.phamvantan.exercise201.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gallery")
// Bỏ qua các thuộc tính của Hibernate khi chuyển đổi JSON
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Gallery {

    // UUID NOT NULL DEFAULT uuid_generate
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "UUID")
    private UUID id;

    // UUID REFERENCES products(id) - Mối quan hệ ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false) // product_id là NOT NULL
    @JsonIgnoreProperties({"galleries", "hibernateLazyInitializer", "handler"}) // Tránh lặp vô hạn và bỏ qua thuộc tính Hibernate
    private Product product;

    // TEXT NOT NULL
    @Column(name = "image", columnDefinition = "TEXT", nullable = false)
    private String image;

    // TEXT NOT NULL
    @Column(name = "placeholder", columnDefinition = "TEXT", nullable = false)
    private String placeholder;

    // BOOLEAN DEFAULT FALSE
    @Column(name = "is_thumbnail", nullable = false)
    private Boolean isThumbnail = false;

    // TIMESTAMPTZ NOT NULL DEFAULT NOW()
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // TIMESTAMPTZ NOT NULL DEFAULT NOW()
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();
}