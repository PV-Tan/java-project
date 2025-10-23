package com.phamvantan.exercise201.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime; 
import java.util.UUID;
import org.hibernate.annotations.JdbcTypeCode; // <-- Thêm import này
import org.hibernate.type.SqlTypes; // <-- Thêm import này

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sideshows")
public class Sideshow {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; 

    @Column(name = "title", length = 80)
    private String title; 

    @Column(name = "destination_url", columnDefinition = "TEXT")
    private String destinationUrl; 

    @Column(name = "image", columnDefinition = "TEXT", nullable = false)
    private String image; 

    @Column(name = "placeholder", columnDefinition = "TEXT", nullable = false)
    private String placeholder; 

    @Column(name = "description", length = 160)
    private String description; 

    @Column(name = "btn_label", length = 50)
    private String btnLabel; 

    @Column(name = "display_order", nullable = false)
    private Integer displayOrder; 

    @Column(name = "published", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean published = false; 

    @Column(name = "clicks", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer clicks = 0; 

    @Column(name = "styles", columnDefinition = "JSONB")
    @JdbcTypeCode(SqlTypes.JSON) // <-- Dòng khắc phục lỗi JSONB/VARCHAR
    private String styles; // String trong Java sẽ được ép kiểu thành JSONB khi lưu

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMPTZ NOT NULL DEFAULT NOW()")
    private OffsetDateTime createdAt = OffsetDateTime.now(); 

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMPTZ NOT NULL DEFAULT NOW()")
    private OffsetDateTime updatedAt = OffsetDateTime.now(); 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    @JsonIgnoreProperties({"createdBy", "updatedBy", "hibernateLazyInitializer", "handler"})
    private StaffAccount createdBy; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    @JsonIgnoreProperties({"createdBy", "updatedBy", "hibernateLazyInitializer", "handler"})
    private StaffAccount updatedBy; 
}