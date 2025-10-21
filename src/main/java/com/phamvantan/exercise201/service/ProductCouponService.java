package com.phamvantan.exercise201.service;

import java.util.List;
import java.util.UUID;

import com.phamvantan.exercise201.entity.ProductCoupon;

public interface ProductCouponService {

    ProductCoupon createProductCoupon(ProductCoupon productCoupon);

    ProductCoupon getProductCouponById(UUID id);

    List<ProductCoupon> getAllProductCoupons();

    // Thêm các phương thức nghiệp vụ khác nếu cần
}