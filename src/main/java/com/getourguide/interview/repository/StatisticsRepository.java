package com.getourguide.interview.repository;

import com.getourguide.interview.entity.Supplier;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends JpaRepository<Supplier, Long> {
    String SUPPLIER_STATS_QUERY = """
        SELECT s.* FROM getyourguide.supplier s
        """;

    @Query(value = SUPPLIER_STATS_QUERY, nativeQuery = true)
    List<Object[]> getSupplierStats();
}
