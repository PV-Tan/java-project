package com.phamvantan.exercise201.service;

import java.util.List;
import java.util.UUID;

import com.phamvantan.exercise201.entity.Coupon;

public interface CouponService {
    
    // Lưu hoặc cập nhật
    Coupon saveCoupon(Coupon coupon);
    
    // Lấy tất cả coupons
    List<Coupon> getAllCoupons();

    // Lấy theo ID
    Coupon getCouponById(UUID id);

    // Lấy theo Code
    Coupon getCouponByCode(String code);

    // Xóa
    void deleteCoupon(UUID id);
}