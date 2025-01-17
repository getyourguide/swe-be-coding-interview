package com.getourguide.interview.controller;

import com.getourguide.interview.dto.Supplier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SupplierController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/suppliers")
    public ResponseEntity<List<Supplier>> suppliers() {
        var list = (List<Supplier>) entityManager.createNativeQuery("SELECT * FROM GETYOURGUIDE.SUPPLIER", Supplier.class).getResultList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/suppliers/search/{search}")
    public ResponseEntity<List<Supplier>> suppliersSearch(@PathVariable String search) {
        var list = (List<Supplier>) entityManager.createNativeQuery("SELECT * FROM GETYOURGUIDE.SUPPLIER", Supplier.class).getResultList();
        for(Supplier s: list) {
            String sb =
                new StringBuilder().append(s.getName()).append(s.getAddress()).append(s.getZip()).append(s.getCity())
                    .append(s.getCountry()).toString();
            if(sb.contains(search)) {
                return ResponseEntity.ok(List.of(s));
            }
        }
        return ResponseEntity.ok(list);
    }
}
