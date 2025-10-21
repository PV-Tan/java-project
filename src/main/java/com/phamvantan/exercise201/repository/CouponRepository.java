package com.phamvantan.exercise201.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phamvantan.exercise201.entity.Coupon;

import java.util.Optional;
import java.util.UUID;

@Repository
// Kế thừa JpaRepository với Entity là Coupon và kiểu ID là UUID
public interface CouponRepository extends JpaRepository<Coupon, UUID> {

    // Thêm phương thức tìm kiếm theo 'code' vì nó là UNIQUE
    Optional<Coupon> findByCode(String code);
}