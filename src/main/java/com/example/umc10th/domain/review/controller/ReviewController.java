package com.example.umc10th.domain.review.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Review", description = "리뷰 API")
@RestController
public class ReviewController {

    @GetMapping("/reviews/test")
    public String test() {
        return "review test success";
    }
}