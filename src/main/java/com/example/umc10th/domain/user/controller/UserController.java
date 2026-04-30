package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

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