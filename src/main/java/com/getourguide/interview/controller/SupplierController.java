package com.getourguide.interview.controller;

import com.getourguide.interview.dto.ErrorResponse;
import com.getourguide.interview.entity.Supplier;
import com.getourguide.interview.service.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Suppliers", description = "Supplier management endpoints")
@RestController
@Validated
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @Operation(summary = "Get all suppliers", description = "Returns list of all suppliers")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping("/suppliers")
    public ResponseEntity<List<Supplier>> suppliers() {
        return ResponseEntity.ok(supplierService.getSuppliers());
    }

    @Operation(summary = "Search suppliers", description = "Search suppliers by name (max 100 chars)")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "400", description = "Invalid search term",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/suppliers/search/{search}")
    public ResponseEntity<List<Supplier>> suppliersSearch(
            @Parameter(description = "Search term", example = "Tours")
            @PathVariable @NotBlank(message = "Search term cannot be blank") @Size(max = 100, message = "Search term too long") String search) {
        return ResponseEntity.ok(supplierService.searchSuppliers(search));
    }
}
