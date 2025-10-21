package com.phamvantan.exercise201.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attribute_values")
public class AttributeValue {

    // id (UUID NOT NULL DEFAULT uuid_generate)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // attribute_id (UUID REFERENCES attributes(id) NOT NULL)
    // Thiết lập quan hệ Many-to-One với Attribute (để lấy thông tin đầy đủ)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id", nullable = false)
    // Loại bỏ các trường gây lỗi lặp vô hạn (recursive JSON)
    @JsonIgnoreProperties({"attributeValues","hibernateLazyInitializer", "handler"}) 
    private Attribute attribute; 

    // attribute_value (VARCHAR(255) NOT NULL)
    @Column(name = "attribute_value", nullable = false, length = 255)
    private String attributeValue;

    // color (VARCHAR(50) DEFAULT NULL)
    @Column(name = "color", length = 50)
    private String color;
}