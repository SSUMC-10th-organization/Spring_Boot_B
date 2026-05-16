package com.example.coco.domain.user.controller;

import com.example.coco.base.ApiResponse;
import com.example.coco.domain.user.dto.UserResponseDTO;
import com.example.coco.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "User API", description = "유저 관련 API (홈, 마이페이지, 미션)")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService; // 분리된 유저 서비스 주입

    @Operation(summary = "홈 화면 조회", description = "토큰 유저의 포인트 및 미션 현황을 조회합니다.")
    @GetMapping("/home")
    public ApiResponse<UserResponseDTO.HomeDTO> getHome(
            @Parameter(description = "페이지 번호 (0부터 시작)", example = "0")
            @RequestParam(name = "page", defaultValue = "0") int page) {

        PageRequest pageRequest = PageRequest.of(page, 10);
        UserResponseDTO.HomeDTO result = userService.getHomeData(1L, pageRequest); // 임시 사용자 ID 1L
        return ApiResponse.onSuccess(result);
    }

    @Operation(summary = "마이페이지 조회", description = "유저 프로필 정보를 조회합니다.")
    @GetMapping("/mypage")
    public ApiResponse<UserResponseDTO.MyPageDTO> getMyPage() {
        UserResponseDTO.MyPageDTO result = userService.getMyPageData(1L); // 임시 사용자 ID 1L
        return ApiResponse.onSuccess(result);
    }

    @Operation(summary = "미션 목록 조회", description = "상태별 미션 목록을 조회합니다.")
    @GetMapping("/missions")
    public ApiResponse<List<UserResponseDTO.MissionDetailDTO>> getMissions(
            @Parameter(description = "미션 상태 (progress 등)") @RequestParam String status,
            @Parameter(description = "페이지 번호 (0부터 시작)", example = "0")
            @RequestParam(name = "page", defaultValue = "0") int page) {

        PageRequest pageRequest = PageRequest.of(page, 10);
        UserResponseDTO.HomeDTO homeData = userService.getHomeData(1L, pageRequest);
        return ApiResponse.onSuccess(homeData.missions());
    }

    @Operation(summary = "미션 성공 처리", description = "미션을 완료 상태로 변경합니다.")
    @PatchMapping("/missions/{missionId}/complete")
    public ApiResponse<String> completeMission(@PathVariable Long missionId) {
        return ApiResponse.onSuccess("미션 완료 성공");
    }
}