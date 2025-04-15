package com.getourguide.interview.controller;

import com.getourguide.interview.service.StatisticsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/stats/suppliers")
    public ResponseEntity<List<Object[]>> supplierStats() {
        return ResponseEntity.ok(statisticsService.getSupplierStats());
    }
}
