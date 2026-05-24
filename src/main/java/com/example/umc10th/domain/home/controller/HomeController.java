package com.example.umc10th.domain.home.controller;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.domain.home.dto.HomeResDTO;
import com.example.umc10th.domain.home.service.HomeService;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class HomeController {

    private final HomeService homeService;

    /**
     * 홈 화면 조회
     * GET /api/v1/home?location_id=1
     * Header: Authorization: Bearer {token}
     */
    @GetMapping("/home")
    public ApiResponse<HomeResDTO.Home> getHome(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("location_id") Long locationId
    ) {
        Long userId = extractUserId(authorization);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, homeService.getHome(userId, locationId));
    }

    private Long extractUserId(String authorization) {
        // TODO: JWT 파싱 로직
        return 1L;
    }
}