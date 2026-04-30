package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.res.HomeResponseDTO;
import com.example.umc10th.domain.mission.service.HomeService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HomeController {
  private final HomeService homeService;

  @GetMapping("/home")
  public ApiResponse<HomeResponseDTO> getHome(@RequestParam String location)
  {
    return ApiResponse.onSuccess(GeneralSuccessCode.OK, homeService.getHome(location));
  }

}
