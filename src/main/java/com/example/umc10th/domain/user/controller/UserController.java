package com.example.umc10th.domain.user.controller;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     * POST /api/v1/auth/signup
     */
    @PostMapping("/auth/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserResDTO.SignUp> signUp(
            @RequestBody @Valid UserReqDTO.SignUp request
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, userService.signUp(request));
    }

    /**
     * 내 프로필 조회
     * GET /api/v1/users/me
     * Header: Authorization: Bearer {token}
     */
    @GetMapping("/users/me")
    public ApiResponse<UserResDTO.MyPage> getMyProfile(
            @RequestHeader("Authorization") String authorization
    ) {
        // TODO: JWT에서 userId 추출 → Spring Security 도입 후 @AuthenticationPrincipal로 교체
        Long userId = extractUserId(authorization);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, userService.getMyProfile(userId));
    }

    private Long extractUserId(String authorization) {
        // TODO: JWT 파싱 로직
        return 1L;
    }
}