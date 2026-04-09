package com.example.umc10th.domain.restaurant.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Restaurant", description = "식당 API")
@RestController
public class RestaurantController {

    @GetMapping("/restaurants/test")
    public String test() {
        return "restaurant test success";
    }
}