package com.example.umc10th.domain.restaurant.dto;

import lombok.Builder;

public class RestaurantResDTO {

    @Builder
    public record CreateRestaurantResponse(
            Long restaurantId,
            String name,
            String address,
            String category
    ) {
    }

    @Builder
    public record GetRestaurantResponse(
            Long restaurantId,
            String name,
            String address,
            String category,
            Double score
    ) {
    }
}