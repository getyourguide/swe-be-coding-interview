package com.getourguide.interview.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Activity information")
@Data
@Builder
public class ActivityDto {
    @Schema(description = "Activity ID", example = "1")
    private Long id;

    @Schema(description = "Activity title", example = "Eiffel Tower Tour")
    private String title;

    @Schema(description = "Price in cents", example = "5000")
    private int price;

    @Schema(description = "Currency code", example = "EUR")
    private String currency;

    @Schema(description = "Rating (0-5)", example = "4.5", minimum = "0", maximum = "5")
    private double rating;

    @Schema(description = "Special offer flag", example = "true")
    private boolean specialOffer;

    @Schema(description = "Supplier name", example = "Paris Tours Inc")
    private String supplierName;
}
