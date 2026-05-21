package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ApiResponse<Void> signUp(
            @RequestBody UserReqDTO.SignUpDTO request
    ) {
        userService.signUp(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.USER_SIGNUP_SUCCESS, null);
    }

    @PostMapping("/me")
    public ApiResponse<UserResDTO.MyPageResponse> getMyPage(
            @RequestBody UserReqDTO.MyPageRequest request
    ) {
        UserResDTO.MyPageResponse response = UserResDTO.MyPageResponse.builder()
                .name("nickname012")
                .profileUrl("https://example.com/profile.png")
                .email("test@test.com")
                .phoneNumber(null)
                .point(2500)
                .build();

        return ApiResponse.onSuccess(GeneralSuccessCode.USER_MY_PAGE_SUCCESS, response);
    }
}