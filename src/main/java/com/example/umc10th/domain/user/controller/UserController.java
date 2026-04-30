package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.req.OnboardingDTO;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
  private UserService userService;

  @PostMapping("/onboarding")
  public ApiResponse<Void> onboarding(
      @RequestBody OnboardingDTO request
      ){
    return ApiResponse.onSuccess(GeneralSuccessCode.OK, userService.onboarding());
  }
}
