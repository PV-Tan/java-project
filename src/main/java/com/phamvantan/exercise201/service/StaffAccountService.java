package com.phamvantan.exercise201.service;

import java.util.List;
import java.util.UUID;

import com.phamvantan.exercise201.entity.StaffAccount;

public interface StaffAccountService {
    List<StaffAccount> findAll();
    StaffAccount findById(UUID id);
    StaffAccount save(StaffAccount account);
    void deleteById(UUID id);
}
