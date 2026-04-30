package com.example.umc10th.domain.restaurant.dto;

public class RestaurantReqDTO {

    public record CreateRestaurantRequest(
            String name,
            String address,
            String category
    ) {
    }

    public record GetRestaurantRequest(
            Long restaurantId
    ) {
    }
}