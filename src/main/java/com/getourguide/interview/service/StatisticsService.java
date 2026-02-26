package com.getourguide.interview.service;

import com.getourguide.interview.entity.Supplier;
import com.getourguide.interview.repository.StatisticsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;

    public List<Supplier> getSupplierStats() {
        return statisticsRepository.findAll();
    }
}
