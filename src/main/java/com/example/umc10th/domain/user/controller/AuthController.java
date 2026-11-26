package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.req.LoginReqDTO;
import com.example.umc10th.domain.user.dto.req.SignUpReqDTO;
import com.example.umc10th.domain.user.dto.res.LoginResDTO;
import com.example.umc10th.domain.user.dto.res.SignUpResDTO;
import com.example.umc10th.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

  private final UserService userService;

  @PostMapping("/signup")
  public ApiResponse<SignUpResDTO> signUp(
      @Valid @RequestBody SignUpReqDTO request
  ) {
    return ApiResponse.onSuccess(
        UserSuccessCode.SIGN_UP_SUCCESS,
        userService.signUp(request)
    );
  }

  @PostMapping("/login")
  public ApiResponse<LoginResDTO> login(
      @Valid @RequestBody LoginReqDTO request
  ) {
    return ApiResponse.onSuccess(
        UserSuccessCode.LOGIN_SUCCESS,
        userService.login(request)
    );
  }
}
