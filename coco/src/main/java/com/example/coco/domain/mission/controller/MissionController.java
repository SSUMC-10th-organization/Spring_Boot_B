package com.example.coco.domain.mission.controller;

import com.example.coco.base.ApiResponse;
import com.example.coco.domain.mission.dto.MissionResponseDTO;
import com.example.coco.domain.mission.service.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest; // 올바른 import
import org.springframework.web.bind.annotation.*;

@Tag(name = "Mission API", description = "미션 목록 조회 및 진행 관련 API")
@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService; // 분리된 미션 서비스 주입

    @Operation(summary = "나의 미션 목록 조회 API", description = "진행 중이거나 완료한 미션 목록을 상태별로 조회합니다.")
    @Parameters({
            @Parameter(name = "status", description = "미션 상태 (progress: 진행중, complete: 완료)", example = "progress"),
            @Parameter(name = "page", description = "페이지 번호 (0부터 시작)", example = "0")
    })
    @GetMapping("")
    public ApiResponse<MissionResponseDTO.MissionListDTO> getMissions(
            @RequestParam(name = "status") String status,
            @RequestParam(name = "page", defaultValue = "0") int page) {

        PageRequest pageRequest = PageRequest.of(page, 10);
        MissionResponseDTO.MissionListDTO result = missionService.getMyMissions(1L, status, pageRequest); // 임시 사용자 ID 1L
        return ApiResponse.onSuccess(result);
    }

    @Operation(summary = "미션 성공 누르기 API", description = "진행 중인 미션을 완료 상태로 변경합니다.")
    @PatchMapping("/{missionId}/complete")
    public ApiResponse<MissionResponseDTO.MissionCompleteResultDTO> completeMission(
            @Parameter(description = "완료 처리할 미션의 ID", example = "1") @PathVariable Long missionId) {

        // 요구사항에 맞춰 형식 구조 유지를 위한 데이터 바인딩
        MissionResponseDTO.MissionCompleteResultDTO result =
                new MissionResponseDTO.MissionCompleteResultDTO(1L, missionId, "complete", "2026-05-16T18:41:00Z");
        return ApiResponse.onSuccess(result);
    }
}