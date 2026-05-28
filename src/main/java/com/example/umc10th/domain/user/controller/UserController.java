package com.example.umc10th.domain.user.controller;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc10th.global.security.auth.AuthUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
            @AuthenticationPrincipal AuthUser authUser
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                userService.getMyProfile(authUser.getUserId())
        );
    }

    @PostMapping("/auth/login")
    public ApiResponse<UserResDTO.Login> login(
            @RequestBody UserReqDTO.Login request
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                userService.login(request)
        );
    }

}