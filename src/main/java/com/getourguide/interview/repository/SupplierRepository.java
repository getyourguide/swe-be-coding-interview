package com.getourguide.interview.repository;

import com.getourguide.interview.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query("SELECT s FROM Supplier s WHERE " +
           "LOWER(s.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(s.address) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(s.city) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(s.country) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(s.zip) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Supplier> searchSupplier(@Param("search") String search);
}
