package org.example.erd.domain.auth.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.example.erd.domain.auth.dto.AuthReqDTO;
import org.example.erd.domain.auth.dto.AuthResDTO;
import org.example.erd.domain.auth.exception.code.AuthSuccessCode;
import org.example.erd.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.erd.domain.auth.dto.AuthResDTO;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Operation(summary = "회원가입", description = "새로운 회원을 등록합니다.")
    @PostMapping("/signup")
    public ApiResponse<AuthResDTO.SignUpRes> signUp(@RequestBody AuthReqDTO.SignUpDTO request) {
        return ApiResponse.onSuccess(AuthSuccessCode.SIGNUP_SUCCESS,null);
    }


}
