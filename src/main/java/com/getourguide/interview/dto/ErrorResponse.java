package com.getourguide.interview.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Schema(description = "Error response")
@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
    @Schema(description = "Error message", example = "Activity ID must be positive")
    private String message;

    @Schema(description = "HTTP status code", example = "400")
    private int status;

    @Schema(description = "Timestamp of error", example = "1677649200000")
    private long timestamp;

    @Schema(description = "Request path", example = "/activities/-1")
    private String path;
}
