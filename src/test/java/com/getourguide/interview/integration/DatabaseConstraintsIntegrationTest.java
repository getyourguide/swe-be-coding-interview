package com.getourguide.interview.integration;

import com.getourguide.interview.entity.Activity;
import com.getourguide.interview.entity.Supplier;
import com.getourguide.interview.repository.ActivityRepository;
import com.getourguide.interview.repository.SupplierRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DatabaseConstraintsIntegrationTest {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private EntityManager entityManager;


    @Test
    @Transactional
    void testForeignKeyViolation_AfterMigration() {
        // After V1.0.3: Cannot insert activity with non-existent supplier_id
        // Should FAIL after migration (FK constraint enforced)
        Exception exception = assertThrows(Exception.class, () -> {
            entityManager.createNativeQuery(
                "INSERT INTO getyourguide.activity (id, title, price, currency, rating, special_offer, supplier_id) " +
                "VALUES (88888, 'Test Activity', 100, 'USD', 4.5, false, 888)")
                .executeUpdate();
            entityManager.flush();
        }, "After V1.0.3: FK enforced, should reject orphaned activity");

        // Verify it's a constraint violation
        assertTrue(exception.getMessage().contains("constraint") ||
                   exception.getMessage().contains("foreign key") ||
                   exception.getMessage().contains("referential"),
                   "Expected FK constraint violation, got: " + exception.getMessage());
    }

    @Test
    @Transactional
    void testNullTitleRejected_AfterMigration() {
        // After V1.0.3: Cannot insert activity with null title
        // Should FAIL after migration (NOT NULL enforced)
        Exception exception = assertThrows(Exception.class, () -> {
            entityManager.createNativeQuery(
                "INSERT INTO getyourguide.activity (id, title, price, currency, rating, special_offer, supplier_id) " +
                "VALUES (88887, NULL, 100, 'USD', 4.5, false, 1)")
                .executeUpdate();
            entityManager.flush();
        }, "After V1.0.3: NOT NULL enforced, should reject null title");

        // Verify it's a NOT NULL constraint violation
        assertTrue(exception.getMessage().contains("NULL") ||
                   exception.getMessage().contains("null") ||
                   exception.getMessage().contains("constraint"),
                   "Expected NOT NULL constraint violation, got: " + exception.getMessage());
    }

    @Test
    @Transactional
    void testSupplierDeletionRestricted_AfterMigration() {
        // Create supplier and activity using native SQL
        entityManager.createNativeQuery(
            "INSERT INTO getyourguide.supplier (id, name, address, zip, city, country) " +
            "VALUES (7771, 'Test Supplier', '123 St', '12345', 'Test City', 'Test Country')")
            .executeUpdate();

        entityManager.createNativeQuery(
            "INSERT INTO getyourguide.activity (id, title, price, currency, rating, special_offer, supplier_id) " +
            "VALUES (77771, 'Test Activity', 100, 'USD', 4.5, false, 7771)")
            .executeUpdate();
        entityManager.flush();

        // After V1.0.3: Cannot delete supplier with activities (FK RESTRICT)
        Exception exception = assertThrows(Exception.class, () -> {
            entityManager.createNativeQuery("DELETE FROM getyourguide.supplier WHERE id = 7771")
                .executeUpdate();
            entityManager.flush();
        }, "After V1.0.3: FK RESTRICT enforced, should prevent supplier deletion");

        // Verify it's a FK constraint violation
        assertTrue(exception.getMessage().contains("constraint") ||
                   exception.getMessage().contains("foreign key") ||
                   exception.getMessage().contains("referential"),
                   "Expected FK RESTRICT violation, got: " + exception.getMessage());
    }
}
