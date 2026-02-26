package com.getourguide.interview.service;

import com.getourguide.interview.repository.SupplierRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.getourguide.interview.helpers.SupplierHelper.createSupplier;
import static org.mockito.Mockito.*;

class SupplierServiceTest {
    private SupplierRepository supplierRepository;
    private SupplierService supplierService;

    @BeforeEach
    void setup() {
        this.supplierRepository = mock(SupplierRepository.class);
        this.supplierService = new SupplierService(supplierRepository);
    }

    @Test
    void testGetSuppliers() {
        var testSupplier = createSupplier(1L, "Test Supplier");
        when(supplierRepository.findAll()).thenReturn(List.of(testSupplier));

        var result = supplierService.getSuppliers();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Test Supplier", result.get(0).getName());
        verify(supplierRepository, times(1)).findAll();
    }

    @Test
    void testSearchSuppliers() {
        var testSupplier = createSupplier(1L, "Berlin Tours");
        when(supplierRepository.searchSupplier("berlin")).thenReturn(List.of(testSupplier));

        var result = supplierService.searchSuppliers("berlin");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Berlin Tours", result.get(0).getName());
        verify(supplierRepository, times(1)).searchSupplier("berlin");
    }

    @Test
    void testSearchSuppliers_NoResults() {
        when(supplierRepository.searchSupplier("nonexistent")).thenReturn(List.of());

        var result = supplierService.searchSuppliers("nonexistent");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(0, result.size());
        verify(supplierRepository, times(1)).searchSupplier("nonexistent");
    }
}
