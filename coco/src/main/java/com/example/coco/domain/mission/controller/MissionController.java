package com.example.coco.domain.mission.controller;

import com.example.coco.base.ApiResponse;
import com.example.coco.domain.mission.dto.MissionResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Mission API", description = "미션 목록 조회 및 진행 관련 API")
@RestController
@RequestMapping("/api/missions")
public class MissionController {

    @Operation(summary = "나의 미션 목록 조회 API", description = "진행 중이거나 완료한 미션 목록을 상태별로 조회합니다.")
    @Parameters({
            @Parameter(name = "status", description = "미션 상태 (progress: 진행중, complete: 완료)", example = "progress")
    })
    @GetMapping("")
    public ApiResponse<MissionResponseDTO.MissionListDTO> getMissions(
            @RequestParam(name = "status") String status) {
        // 차주 Service 로직 구현 예정
        return ApiResponse.onSuccess(null);
    }

    @Operation(summary = "미션 성공 누르기 API", description = "진행 중인 미션을 완료 상태로 변경합니다.")
    @PatchMapping("/{missionId}/complete")
    public ApiResponse<MissionResponseDTO.MissionCompleteResultDTO> completeMission(
            @Parameter(description = "완료 처리할 미션의 ID", example = "1") @PathVariable Long missionId) {
        // 차주 Service 로직 구현 예정
        return ApiResponse.onSuccess(null);
    }
}