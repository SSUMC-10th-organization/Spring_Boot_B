package com.example.umc10th.domain.review.controller;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ApiResponse<ReviewResponseDto.ReviewResult> createReview(
            @PathVariable Long restaurantId,
            @RequestBody ReviewRequestDto.CreateDto request,
            @RequestParam Long userId
    ) {
        Review review = reviewService.createReview(userId, restaurantId, request);
        return ApiResponse.onSuccess(ReviewConverter.toReviewResult(review));
    }
