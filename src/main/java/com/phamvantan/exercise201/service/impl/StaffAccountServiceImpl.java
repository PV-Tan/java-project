package com.phamvantan.exercise201.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.phamvantan.exercise201.entity.StaffAccount;
import com.phamvantan.exercise201.repository.StaffAccountRepository;
import com.phamvantan.exercise201.service.StaffAccountService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StaffAccountServiceImpl implements StaffAccountService {

    private final StaffAccountRepository staffAccountRepository;

    @Override
    public List<StaffAccount> findAll() {
        return staffAccountRepository.findAll();
    }

    @Override
    public StaffAccount findById(UUID id) {
        return staffAccountRepository.findById(id).orElse(null);
    }

    @Override
    public StaffAccount save(StaffAccount account) {
        return staffAccountRepository.save(account);
    }

    @Override
    public void deleteById(UUID id) {
        staffAccountRepository.deleteById(id);
    }
}
