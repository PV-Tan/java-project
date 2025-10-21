package com.phamvantan.exercise201.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

// Sử dụng Lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// Đánh dấu Entity
@Entity
// Ánh xạ tới bảng 'coupons'
@Table(name = "coupons")
public class Coupon {

    // ID tự động sinh UUID theo yêu cầu
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; // id: UUID NOT NULL DEFAULT uuid_generate

    // Tên thuộc tính camelCase, ánh xạ đúng tên cột DB
    @Column(name = "code", length = 50, nullable = false, unique = true)
    private String code; // code: VARCHAR(50) NOT NULL UNIQUE

    // Sử dụng BigDecimal cho giá trị chiết khấu (NUMERIC)
    @Column(name = "discount_value")
    private BigDecimal discountValue; // discount_value: NUMERIC

    @Column(name = "discount_type", length = 50, nullable = false)
    private String discountType; // discount_type: VARCHAR(50) NOT NULL

    // Sử dụng Integer cho số lần sử dụng, đặt giá trị mặc định là 0
    @Column(name = "times_used", nullable = false)
    private Integer timesUsed = 0; // times_used: NUMERIC NOT NULL DEFAULT 0

    @Column(name = "max_usage")
    private Integer maxUsage; // max_usage: NUMERIC DEFAULT null

    // Sử dụng BigDecimal cho giới hạn đơn hàng
    @Column(name = "order_amount_limit")
    private BigDecimal orderAmountLimit; // order_amount_limit: NUMERIC DEFAULT null

    // Sử dụng OffsetDateTime cho TIMESTAMPTZ
    @Column(name = "coupon_start_date")
    private OffsetDateTime couponStartDate; // coupon_start_date: TIMESTAMPTZ

    @Column(name = "coupon_end_date")
    private OffsetDateTime couponEndDate; // coupon_end_date: TIMESTAMPTZ
}