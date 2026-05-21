package com.example.umc10th.domain.home.controller;

import com.example.umc10th.domain.home.dto.HomeReqDTO;
import com.example.umc10th.domain.home.dto.HomeResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurants")
public class HomeController {

    @PostMapping
    public ApiResponse<HomeResDTO.CreateRestaurantResponse> createRestaurant(
            @RequestBody HomeReqDTO.CreateRestaurantRequest request
    ) {
        HomeResDTO.CreateRestaurantResponse response =
                HomeResDTO.CreateRestaurantResponse.builder()
                        .restaurantId(1L)
                        .name(request.name())
                        .address(request.address())
                        .category(request.category())
                        .build();

        return ApiResponse.onSuccess(GeneralSuccessCode.RESTAURANT_CREATE_SUCCESS, response);
    }

    @PostMapping("/detail")
    public ApiResponse<HomeResDTO.GetRestaurantResponse> getRestaurant(
            @RequestBody HomeReqDTO.GetRestaurantRequest request
    ) {
        HomeResDTO.GetRestaurantResponse response =
                HomeResDTO.GetRestaurantResponse.builder()
                        .restaurantId(request.restaurantId())
                        .name("맛있는 식당")
                        .address("서울특별시 동작구")
                        .category("한식")
                        .score(4.5)
                        .build();

        return ApiResponse.onSuccess(GeneralSuccessCode.RESTAURANT_GET_SUCCESS, response);
    }
}