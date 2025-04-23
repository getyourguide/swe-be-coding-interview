package com.getourguide.interview.service;

import com.getourguide.interview.repository.StatisticsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;

    public List<Object[]> getSupplierStats() {
        return statisticsRepository.getSupplierStats();
    }
}
