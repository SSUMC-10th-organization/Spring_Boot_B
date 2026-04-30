package com.example.umc10th.domain.restaurant.controller;

import com.example.umc10th.domain.restaurant.dto.RestaurantReqDTO;
import com.example.umc10th.domain.restaurant.dto.RestaurantResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    @PostMapping
    public ApiResponse<RestaurantResDTO.CreateRestaurantResponse> createRestaurant(
            @RequestBody RestaurantReqDTO.CreateRestaurantRequest request
    ) {
        RestaurantResDTO.CreateRestaurantResponse response =
                RestaurantResDTO.CreateRestaurantResponse.builder()
                        .restaurantId(1L)
                        .name(request.name())
                        .address(request.address())
                        .category(request.category())
                        .build();

        return ApiResponse.onSuccess(GeneralSuccessCode.RESTAURANT_CREATE_SUCCESS, response);
    }

    @PostMapping("/detail")
    public ApiResponse<RestaurantResDTO.GetRestaurantResponse> getRestaurant(
            @RequestBody RestaurantReqDTO.GetRestaurantRequest request
    ) {
        RestaurantResDTO.GetRestaurantResponse response =
                RestaurantResDTO.GetRestaurantResponse.builder()
                        .restaurantId(request.restaurantId())
                        .name("맛있는 식당")
                        .address("서울특별시 동작구")
                        .category("한식")
                        .score(4.5)
                        .build();

        return ApiResponse.onSuccess(GeneralSuccessCode.RESTAURANT_GET_SUCCESS, response);
    }
}