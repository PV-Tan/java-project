package com.phamvantan.exercise201.service;

import com.phamvantan.exercise201.entity.Sideshow;
import java.util.List;
import java.util.UUID;

public interface SideshowService {
    List<Sideshow> findAll();
    Sideshow findById(UUID id); // Trả về Sideshow hoặc null
    Sideshow save(Sideshow sideshow);
    void deleteById(UUID id);
}