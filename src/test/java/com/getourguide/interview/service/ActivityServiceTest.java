package com.getourguide.interview.service;

import com.getourguide.interview.controller.SupplierController;
import com.getourguide.interview.repository.ActivityRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.getourguide.interview.helpers.ActivityHelper.createActivity;
import static com.getourguide.interview.helpers.SupplierHelper.createSupplier;
import static org.mockito.Mockito.*;

class ActivityServiceTest {
    private ActivityRepository activityRepository;
    private SupplierController supplierController;
    private ActivityService activityService;

    @BeforeEach
    void setup() {
        this.activityRepository = mock(ActivityRepository.class);
        this.supplierController = mock(SupplierController.class);
        this.activityService = new ActivityService(activityRepository);
    }

    @Test
    void testGetActivities() {
        var testActivity = createActivity(
            1L,
            "Test Activity",
            100,
            5.0,
            false,
            createSupplier(1L, "Test Supplier")
        );
        when(activityRepository.findAll()).thenReturn(List.of(testActivity));

        var result = activityService.getActivities();

        Assertions.assertNotNull(result);
    }
}
