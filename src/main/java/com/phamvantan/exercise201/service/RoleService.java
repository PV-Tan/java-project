package com.phamvantan.exercise201.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.phamvantan.exercise201.entity.Role;

public interface RoleService {
    List<Role> findAll();
    Optional<Role> findById(UUID id);
    Role save(Role role);
    void deleteById(UUID id);
}
