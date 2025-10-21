package com.phamvantan.exercise201.service;

import java.util.List;
import java.util.UUID;

import com.phamvantan.exercise201.entity.ProductCategory;

public interface ProductCategoryService {
    
    // ✅ Sửa: Đổi tên và kiểu trả về để Service có thể trả về đối tượng đã lưu
    ProductCategory createRelationship(UUID productId, UUID categoryId);

    // ✅ Sửa: Đổi kiểu trả về từ Object sang List<ProductCategory>
    List<ProductCategory> findAll(); 
    
    List<ProductCategory> getCategoriesByProduct(UUID productId);

    List<ProductCategory> getProductsByCategory(UUID categoryId);
    
    // Loại bỏ phương thức cũ (hoặc giữ lại nếu bạn vẫn dùng ở đâu đó, 
    // nhưng thường chỉ cần 1 phương thức cho 1 mục đích)
    // void addProductToCategory(UUID productId, UUID categoryId);

    void removeProductFromCategory(UUID productId, UUID categoryId);

    boolean existsProductInCategory(UUID productId, UUID categoryId);
}