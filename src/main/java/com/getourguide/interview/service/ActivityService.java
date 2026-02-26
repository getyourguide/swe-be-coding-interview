package com.getourguide.interview.service;

import com.getourguide.interview.dto.ActivityDto;
import com.getourguide.interview.entity.Activity;
import com.getourguide.interview.exceptions.ResourceNotFoundException;
import com.getourguide.interview.repository.ActivityRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;

    public List<ActivityDto> getActivities() {
        List<Activity> activities = activityRepository.findAllWithSupplier();
        List<ActivityDto> result = new ArrayList<>();
        activities.forEach(activity -> {
            result.add(ActivityDto.builder()
                    .id(activity.getId())
                    .title(activity.getTitle())
                    .price(activity.getPrice())
                    .currency(activity.getCurrency())
                    .rating(activity.getRating())
                    .specialOffer(activity.isSpecialOffer())
                    .supplierName(Objects.isNull(activity.getSupplier()) ? "" : activity.getSupplier().getName())
                    .build());
        });
        return result;
    }

    public ActivityDto getActivities(Long activityId) {
        Activity activity = activityRepository.findByIdWithSupplier(activityId)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found with id: " + activityId));
        return ActivityDto.builder()
                .id(activity.getId())
                .title(activity.getTitle())
                .price(activity.getPrice())
                .currency(activity.getCurrency())
                .rating(activity.getRating())
                .specialOffer(activity.isSpecialOffer())
                .supplierName(Objects.isNull(activity.getSupplier()) ? "" : activity.getSupplier().getName())
                .build();
    }

    public List<ActivityDto> searchActivities(String search) {
        List<Activity> activities = activityRepository.search(search);
        List<ActivityDto> result = new ArrayList<>();
        activities.forEach(activity -> {
            result.add(ActivityDto.builder()
                .id(activity.getId())
                .title(activity.getTitle())
                .price(activity.getPrice())
                .currency(activity.getCurrency())
                .rating(activity.getRating())
                .specialOffer(activity.isSpecialOffer())
                .supplierName(activity.getSupplier().getName())
                .build());
        });
        return result;
    }
}
