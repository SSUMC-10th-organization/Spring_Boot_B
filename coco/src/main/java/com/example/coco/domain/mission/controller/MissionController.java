package com.example.coco.domain.mission.controller;

import com.example.coco.base.ApiResponse;
import com.example.coco.domain.mission.dto.MissionRequestDTO;
import com.example.coco.domain.mission.dto.MissionResponseDTO;
import com.example.coco.domain.mission.service.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Mission API", description = "미션 목록 조회 및 진행 관련 API")
@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @Operation(summary = "내가 진행중인 미션 조회하기 (오프셋)", description = "바디로 유저 ID를 받아 오프셋 방식으로 진행 중인 미션을 페이징 조회합니다.")
    @Parameters({
            @Parameter(name = "status", description = "미션 상태 (progress: 진행중, complete: 완료)", example = "progress")
    })
    @PostMapping("/my-offset")
    public ApiResponse<MissionResponseDTO.MissionListDTO> getMyMissionsOffset(
            @Valid @RequestBody MissionRequestDTO.MyMissionOptsDTO request,
            @RequestParam(name = "status", defaultValue = "progress") String status) {

        MissionResponseDTO.MissionListDTO result = missionService.getMyMissionsOffset(request, status);
        return ApiResponse.onSuccess(result);
    }

    @Operation(summary = "미션 성공 누르기 API", description = "진행 중인 미션을 완료 상태로 변경합니다.")
    @PatchMapping("/{missionId}/complete")
    public ApiResponse<MissionResponseDTO.MissionCompleteResultDTO> completeMission(
            @Parameter(description = "완료 처리할 미션의 ID", example = "1") @PathVariable Long missionId) {

        MissionResponseDTO.MissionCompleteResultDTO result =
                new MissionResponseDTO.MissionCompleteResultDTO(1L, missionId, "complete", "2026-05-16T18:41:00Z");
        return ApiResponse.onSuccess(result);
    }
}