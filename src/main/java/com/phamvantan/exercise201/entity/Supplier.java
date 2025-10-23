package com.phamvantan.exercise201.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "supplier_name", nullable = false, length = 255)
    private String supplier_name;

    @Column(name = "contact_name", length = 255)
    private String contact_name;

    @Column(name = "contact_title", length = 255)
    private String contact_title;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "city", length = 100)
    private String city;

    // Khóa ngoại đến bảng countries
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Country country;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "fax", length = 50)
    private String fax;

    @Column(name = "homepage", columnDefinition = "TEXT")
    private String homepage;

    @Column(name = "created_at")
    private LocalDateTime created_at = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updated_at = LocalDateTime.now();

    // created_by (FK → staff_accounts)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private StaffAccount created_by;

    // updated_by (FK → staff_accounts)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private StaffAccount updated_by;
}
