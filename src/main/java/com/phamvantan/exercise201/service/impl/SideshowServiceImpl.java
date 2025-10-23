package com.phamvantan.exercise201.service.impl;

import com.phamvantan.exercise201.entity.Sideshow;
import com.phamvantan.exercise201.repository.SideshowRepository;
import com.phamvantan.exercise201.service.SideshowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SideshowServiceImpl implements SideshowService {
    private final SideshowRepository sideshowRepository;

    @Override
    public List<Sideshow> findAll() {
        return sideshowRepository.findAll();
    }

    @Override
    public Sideshow findById(UUID id) {
        // Trả về Sideshow hoặc null (theo định nghĩa ban đầu của bạn)
        return sideshowRepository.findById(id).orElse(null);
    }

    @Override
    public Sideshow save(Sideshow sideshow) {
        return sideshowRepository.save(sideshow);
    }

    @Override
    public void deleteById(UUID id) {
        sideshowRepository.deleteById(id);
    }
}