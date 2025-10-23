package com.phamvantan.exercise201.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.phamvantan.exercise201.entity.Supplier;
import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {

    @Query("SELECT COUNT(s) > 0 FROM Supplier s WHERE s.supplier_name = :supplier_name")
    boolean existsBySupplier_name(String supplier_name);
}
