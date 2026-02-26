package com.getourguide.interview.controller;

import com.getourguide.interview.entity.Supplier;
import com.getourguide.interview.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Statistics", description = "Statistics endpoints")
@RestController
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;

    @Operation(summary = "Get supplier statistics", description = "Returns supplier statistics")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping("/stats/suppliers")
    public ResponseEntity<List<Supplier>> supplierStats() {
        return ResponseEntity.ok(statisticsService.getSupplierStats());
    }
}
