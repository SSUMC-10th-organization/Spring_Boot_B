package com.example.coco.domain.auth.controller;

import com.example.coco.base.ApiResponse;
import com.example.coco.domain.auth.dto.AuthRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth API", description = "인증 API")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Operation(summary = "회원가입", description = "새로운 회원을 등록합니다.")
    @PostMapping("/signup")
    public ApiResponse<Long> signUp(@RequestBody AuthRequestDTO.SignUpDTO request) {
        return ApiResponse.onSuccess(1L);
    }
}