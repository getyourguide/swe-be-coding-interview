package com.getourguide.interview.controller;

import com.getourguide.interview.dto.ActivityDto;
import com.getourguide.interview.dto.ErrorResponse;
import com.getourguide.interview.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Activities", description = "Activity management endpoints")
@RestController
@Validated
@AllArgsConstructor
public class ActivitiesController {

    private final ActivityService activityService;

    @Operation(summary = "Get all activities", description = "Returns list of all activities")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping("/activities")
    public ResponseEntity<List<ActivityDto>> activities() {
        return ResponseEntity.ok(activityService.getActivities());
    }

    @Operation(summary = "Get activity by ID", description = "Returns a single activity by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "400", description = "Invalid ID",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Activity not found",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/activities/{id}")
    public ResponseEntity<ActivityDto> activities(
            @Parameter(description = "Activity ID (must be positive)", example = "1")
            @PathVariable @Positive(message = "Activity ID must be positive") Long id) {
        return ResponseEntity.ok(activityService.getActivities(id));
    }

    @Operation(summary = "Search activities", description = "Search activities by title (max 100 chars)")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "400", description = "Invalid search term",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/activities/search/{search}")
    public ResponseEntity<List<ActivityDto>> activitiesSearch(
            @Parameter(description = "Search term", example = "Paris")
            @PathVariable @NotBlank(message = "Search term cannot be blank") @Size(max = 100, message = "Search term too long") String search) {
        return ResponseEntity.ok(activityService.searchActivities(search));
    }
}
