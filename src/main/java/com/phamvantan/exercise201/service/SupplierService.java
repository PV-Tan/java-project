package com.phamvantan.exercise201.service;

import com.phamvantan.exercise201.entity.Supplier;
import java.util.List;
import java.util.UUID;

public interface SupplierService {
    List<Supplier> findAll();
    Supplier findById(UUID id);
    Supplier save(Supplier supplier);
    void deleteById(UUID id);
}
