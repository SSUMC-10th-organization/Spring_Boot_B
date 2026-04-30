package com.example.coco.domain.user.controller;

import com.example.coco.base.ApiResponse;
import com.example.coco.domain.user.dto.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "User API", description = "유저 관련 API (홈, 마이페이지, 미션)")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Operation(summary = "홈 화면 조회", description = "토큰 유저의 포인트 및 미션 현황을 조회합니다.")
    @GetMapping("/home")
    public ApiResponse<UserResponseDTO.HomeDTO> getHome() {
        return ApiResponse.onSuccess(null);
    }

    @Operation(summary = "마이페이지 조회", description = "유저 프로필 정보를 조회합니다.")
    @GetMapping("/mypage")
    public ApiResponse<UserResponseDTO.MyPageDTO> getMyPage() {
        return ApiResponse.onSuccess(null);
    }

    @Operation(summary = "미션 목록 조회", description = "상태별 미션 목록을 조회합니다.")
    @GetMapping("/missions")
    public ApiResponse<List<UserResponseDTO.MissionDetailDTO>> getMissions(
            @Parameter(description = "미션 상태 (progress 등)") @RequestParam String status) {
        return ApiResponse.onSuccess(null);
    }

    @Operation(summary = "미션 성공 처리", description = "미션을 완료 상태로 변경합니다.")
    @PatchMapping("/missions/{missionId}/complete")
    public ApiResponse<String> completeMission(@PathVariable Long missionId) {
        return ApiResponse.onSuccess("미션 완료 성공");
    }
}