package com.getourguide.interview.service;

import com.getourguide.interview.entity.Supplier;
import com.getourguide.interview.repository.SupplierRepository;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.InjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupplierService {

    SupplierRepository supplierRepository;

    public List<Supplier> getSuppliers() {
        return supplierRepository.findAll();
    }

    public List<Supplier> searchSuppliers(String search) {
        return supplierRepository.searchSupplier(search);
    }
}
