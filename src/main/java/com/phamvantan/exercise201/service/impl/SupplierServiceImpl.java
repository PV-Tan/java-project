package com.phamvantan.exercise201.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.phamvantan.exercise201.entity.Supplier;
import com.phamvantan.exercise201.repository.SupplierRepository;
import com.phamvantan.exercise201.service.SupplierService;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier findById(UUID id) {
        return supplierRepository.findById(id).orElse(null);
    }

    @Override
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public void deleteById(UUID id) {
        supplierRepository.deleteById(id);
    }
}
