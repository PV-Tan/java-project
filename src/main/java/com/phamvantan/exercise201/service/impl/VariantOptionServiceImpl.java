package com.phamvantan.exercise201.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.phamvantan.exercise201.entity.VariantOption;
import com.phamvantan.exercise201.repository.VariantOptionRepository;
import com.phamvantan.exercise201.service.VariantOptionService;

import java.util.List;
import java.util.UUID;

@Service
public class VariantOptionServiceImpl implements VariantOptionService {

    private final VariantOptionRepository variantOptionRepository;

    // Dependency Injection qua constructor
    public VariantOptionServiceImpl(VariantOptionRepository variantOptionRepository) {
        this.variantOptionRepository = variantOptionRepository;
    }

    @Override
    public VariantOption createVariantOption(VariantOption variantOption) {
        // Có thể thêm logic kiểm tra ràng buộc (ví dụ: compare_price >= sale_price) ở đây
        return variantOptionRepository.save(variantOption);
    }

    @Override
    public VariantOption getVariantOptionById(UUID id) {
        // Khi entity được trả về, mối quan hệ ManyToOne (product, image) sẽ 
        // tự động được tải (nếu không lazy) hoặc được truy cập (nếu lazy và được gọi trong transaction)
        return variantOptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VariantOption not found with id: " + id));
    }

    @Override
    public List<VariantOption> getAllVariantOptions() {
        return variantOptionRepository.findAll();
    }

    @Override
    public VariantOption updateVariantOption(UUID id, VariantOption variantOptionDetails) {
        VariantOption existingOption = variantOptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VariantOption not found with id: " + id));

        existingOption.setTitle(variantOptionDetails.getTitle());
        existingOption.setSalePrice(variantOptionDetails.getSalePrice());
        existingOption.setComparePrice(variantOptionDetails.getComparePrice());
        existingOption.setBuyingPrice(variantOptionDetails.getBuyingPrice());
        existingOption.setQuantity(variantOptionDetails.getQuantity());
        existingOption.setSku(variantOptionDetails.getSku());
        existingOption.setActive(variantOptionDetails.getActive());
        
        // Cập nhật các mối quan hệ nếu cần (product, image)
        existingOption.setProduct(variantOptionDetails.getProduct()); 
        existingOption.setImage(variantOptionDetails.getImage());

        return variantOptionRepository.save(existingOption);
    }

    @Override
    public void deleteVariantOption(UUID id) {
        if (!variantOptionRepository.existsById(id)) {
            throw new EntityNotFoundException("VariantOption not found with id: " + id);
        }
        variantOptionRepository.deleteById(id);
    }
}