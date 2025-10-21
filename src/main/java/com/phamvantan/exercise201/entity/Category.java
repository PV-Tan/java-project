package com.phamvantan.exercise201.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Tự tham chiếu: UUID REFERENCES categories(id) ON DELETE SET NULL 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id") // Tên cột trong DB là parent_id 
    @JsonIgnoreProperties({"parent", "createdBy", "updatedBy", "hibernateLazyInitializer", "handler"})
    private Category parent;

    // category_name: VARCHAR(255) NOT NULL UNIQUE 
    @Column(name = "category_name", nullable = false, unique = true, length = 255)
    private String categoryName; // Đã đổi tên biến Java từ 'name' sang 'categoryName'

    // category_description: TEXT 
    @Column(name = "category_description", columnDefinition = "TEXT")
    private String categoryDescription; // Đã đổi tên biến Java từ 'description' sang 'categoryDescription'

    // icon: TEXT 
    @Column(name = "icon", columnDefinition = "TEXT")
    private String icon;

    // image: TEXT 
    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

    // placeholder: TEXT 
    @Column(name = "placeholder", columnDefinition = "TEXT")
    private String placeholder;

    // active: BOOLEAN DEFAULT TRUE 
    @Column(name = "active", nullable = false)
    private Boolean active = true;

    // created_at: TIMESTAMPTZ NOT NULL DEFAULT NOW() 
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // updated_at: TIMESTAMPTZ NOT NULL DEFAULT NOW() 
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    // created_by: UUID REFERENCES staff_accounts(id) 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    @JsonIgnoreProperties({"createdBy", "updatedBy", "hibernateLazyInitializer", "handler"})
    private StaffAccount createdBy;

    // updated_by: UUID REFERENCES staff_accounts(id) 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    @JsonIgnoreProperties({"createdBy", "updatedBy", "hibernateLazyInitializer", "handler"})
    private StaffAccount updatedBy;
}