package com.example.umc10th.domain.store.controller;

import com.example.umc10th.domain.store.converter.StoreConverter;
import com.example.umc10th.domain.store.dto.StoreRequestDTO;
import com.example.umc10th.domain.store.dto.StoreResponseDTO;
import com.example.umc10th.domain.store.entity.Review;
import com.example.umc10th.domain.store.service.ReviewCommandService;
import com.example.umc10th.domain.store.service.ReviewQueryService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
@Validated
@Tag(name = "Store API", description = "가게 관련 API")
public class StoreController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping("/{storeId}/reviews")
    @Operation(summary = "가게 리뷰 작성 API", description = "특정 가게에 리뷰를 작성하는 API입니다.")
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다.")
    })
    public ApiResponse<StoreResponseDTO.ReviewResultDto> createReview(
            @PathVariable Long storeId,
            @Valid @RequestBody StoreRequestDTO.ReviewDto request) {
        
        Review review = reviewCommandService.createReview(request.getMemberId(), storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toReviewResultDto(review.getId()));
    }

    @GetMapping("/reviews/me")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "내가 작성한 리뷰 목록을 커서 기반 페이징으로 조회합니다. 정렬 조건(id 또는 star)을 지정할 수 있습니다.")
    @Parameters({
            @Parameter(name = "memberId", description = "조회할 회원의 ID", example = "1"),
            @Parameter(name = "cursor", description = "마지막으로 조회된 항목의 커서 값. 처음 조회 시 생략"),
            @Parameter(name = "sortBy", description = "정렬 기준 (id: 최신순, star: 별점순)", example = "id"),
            @Parameter(name = "size", description = "조회할 개수", example = "10")
    })
    public ApiResponse<StoreResponseDTO.ReviewListDto> getMyReviews(
            @RequestParam(name = "memberId") Long memberId,
            @RequestParam(name = "cursor", required = false) String cursor,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        StoreResponseDTO.ReviewListDto response = reviewQueryService.getMyReviews(memberId, cursor, sortBy, size);
        return ApiResponse.onSuccess(response);
    }
}
