package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
public class MissionController {

    private final MissionService missionService;

    /**
     * 내 미션 목록 조회
     * GET /api/v1/missions/me?status=IN_PROGRESS&cursor=10&size=10
     * Header: Authorization: Bearer {token}
     */
    @PostMapping("/me")
    public ApiResponse<MissionResDTO.MyMissionList> getMyMissions(
            @RequestBody @Valid MissionReqDTO.MyMissionRequest request,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionService.getMyMissions(request, page, size));
    }

        /**
     * 미션 도전 시작
     * POST /api/v1/missions/{missionId}/start
     * Header: Authorization: Bearer {token}
     */

    @PostMapping("/{missionId}/start")
    public ApiResponse<MissionResDTO.UserMission> startMission(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long missionId
    ) {
        Long userId = extractUserId(authorization);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionService.startMission(userId, missionId));
    }

    /**
     * 미션 취소
     * PATCH /api/v1/missions/me/{userMissionId}/cancel
     * Header: Authorization: Bearer {token}
     */
    @PatchMapping("/me/{userMissionId}/cancel")
    public ApiResponse<MissionResDTO.UserMission> cancelMission(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long userMissionId
    ) {
        Long userId = extractUserId(authorization);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionService.cancelMission(userId, userMissionId));
    }

    private Long extractUserId(String authorization) {
        // TODO: JWT 파싱 로직
        return 1L;
    }
}