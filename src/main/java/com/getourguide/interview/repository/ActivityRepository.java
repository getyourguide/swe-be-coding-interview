package com.getourguide.interview.repository;

import com.getourguide.interview.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    @Query("SELECT a FROM Activity a WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Activity> search(@Param("search") String search);

    @Query("SELECT a FROM Activity a LEFT JOIN FETCH a.supplier")
    List<Activity> findAllWithSupplier();

    @Query("SELECT a FROM Activity a LEFT JOIN FETCH a.supplier WHERE a.id = :id")
    Optional<Activity> findByIdWithSupplier(@Param("id") Long id);
}
