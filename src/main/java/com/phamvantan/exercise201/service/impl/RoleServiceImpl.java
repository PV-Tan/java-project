package com.phamvantan.exercise201.service.impl;

import org.springframework.stereotype.Service;

import com.phamvantan.exercise201.entity.Role;
import com.phamvantan.exercise201.repository.RoleRepository;
import com.phamvantan.exercise201.service.RoleService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteById(UUID id) {
        roleRepository.deleteById(id);
    }
}
