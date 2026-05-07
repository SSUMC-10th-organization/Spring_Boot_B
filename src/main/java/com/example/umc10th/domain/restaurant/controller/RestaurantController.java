package com.example.umc10th.domain.restaurant.controller;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    // 주변 식당 목록 조회
    @GetMapping("/nearby")
    public ApiResponse<Page<RestaurantResponseDto.RestaurantResult>> getNearby(
            @RequestParam Long locationId,
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Restaurant> restaurants =
                restaurantService.getRestaurantsByLocation(locationId, pageable);
        return ApiResponse.onSuccess(
                restaurants.map(RestaurantConverter::toRestaurantResult)
        );
    }

    // 식당 상세 조회
    @GetMapping("/{restaurantId}")
    public ApiResponse<RestaurantResponseDto.RestaurantDetailResult> getDetail(
            @PathVariable Long restaurantId
    ) {
        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
        return ApiResponse.onSuccess(
                RestaurantConverter.toRestaurantDetailResult(restaurant)
        );
    }