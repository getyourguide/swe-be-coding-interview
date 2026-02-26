package com.getourguide.interview.service;

import com.getourguide.interview.repository.StatisticsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.getourguide.interview.helpers.SupplierHelper.createSupplier;
import static org.mockito.Mockito.*;

class StatisticsServiceTest {
    private StatisticsRepository statisticsRepository;
    private StatisticsService statisticsService;

    @BeforeEach
    void setup() {
        this.statisticsRepository = mock(StatisticsRepository.class);
        this.statisticsService = new StatisticsService(statisticsRepository);
    }

    @Test
    void testGetSupplierStats() {
        var supplier1 = createSupplier(1L, "Supplier One");
        var supplier2 = createSupplier(2L, "Supplier Two");
        when(statisticsRepository.findAll()).thenReturn(List.of(supplier1, supplier2));

        var result = statisticsService.getSupplierStats();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        verify(statisticsRepository, times(1)).findAll();
    }

    @Test
    void testGetSupplierStats_EmptyList() {
        when(statisticsRepository.findAll()).thenReturn(List.of());

        var result = statisticsService.getSupplierStats();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(0, result.size());
        verify(statisticsRepository, times(1)).findAll();
    }
}
