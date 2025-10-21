package com.phamvantan.exercise201.service.impl;

import lombok.RequiredArgsConstructor; // ✅ Thêm Lombok để dùng Constructor Injection
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // ✅ Thêm cho phương thức DELETE

import com.phamvantan.exercise201.entity.Category;
import com.phamvantan.exercise201.entity.Product;
import com.phamvantan.exercise201.entity.ProductCategory;
import com.phamvantan.exercise201.repository.CategoryRepository;
import com.phamvantan.exercise201.repository.ProductCategoryRepository;
import com.phamvantan.exercise201.repository.ProductRepository;
import com.phamvantan.exercise201.service.ProductCategoryService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor // Dùng Constructor Injection thay cho @Autowired
public class ProductCategoryServiceImpl implements ProductCategoryService {

    // Final fields được tự động inject nhờ @RequiredArgsConstructor
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // --- Phương thức POST (Tạo mối quan hệ) ---
    // ✅ Sửa: Triển khai phương thức mới từ Interface (đã đổi tên)
    @Override
    public ProductCategory createRelationship(UUID productId, UUID categoryId) {
        // 1. Tìm Product và Category (giữ nguyên logic tìm kiếm)
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        // 2. Kiểm tra tồn tại (giữ nguyên logic)
        if (productCategoryRepository.existsByProduct_IdAndCategory_Id(productId, categoryId)) {
            // Tùy chọn: Có thể ném lỗi hoặc chỉ trả về null nếu đã tồn tại
             throw new RuntimeException("Relationship already exists for Product " + productId + " and Category " + categoryId);
        }

        // 3. Tạo đối tượng ProductCategory mới (Không cần truyền 2 tham số nếu đã xóa constructor cũ)
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProduct(product);
        productCategory.setCategory(category);
        
        // 4. ✅ Sửa: Lưu và TRẢ VỀ đối tượng đã được lưu (chứa ID tự động sinh)
        return productCategoryRepository.save(productCategory);
    }
    
    // --- Phương thức GET (Lấy tất cả) ---
    // ✅ Sửa: Đã đổi kiểu trả về sang List<ProductCategory> và giữ nguyên logic
    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }
    
    // --- Các phương thức khác (Giữ nguyên) ---
    
    @Override
    public List<ProductCategory> getCategoriesByProduct(UUID productId) {
        return productCategoryRepository.findByProduct_Id(productId);
    }

    @Override
    public List<ProductCategory> getProductsByCategory(UUID categoryId) {
        return productCategoryRepository.findByCategory_Id(categoryId);
    }

    // Phương thức cũ addProductToCategory() đã bị loại bỏ hoặc thay thế bằng createRelationship()
    
    @Override
    @Transactional // Cần @Transactional nếu Repository thực hiện lệnh DELETE tùy chỉnh
    public void removeProductFromCategory(UUID productId, UUID categoryId) {
        productCategoryRepository.deleteByProduct_IdAndCategory_Id(productId, categoryId);
    }

    @Override
    public boolean existsProductInCategory(UUID productId, UUID categoryId) {
        return productCategoryRepository.existsByProduct_IdAndCategory_Id(productId, categoryId);
    }
}