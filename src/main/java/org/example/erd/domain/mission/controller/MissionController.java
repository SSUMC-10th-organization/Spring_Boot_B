package org.example.erd.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.erd.domain.mission.dto.MissionResDTO;
import org.example.erd.domain.mission.enums.MissionStatus;
import org.example.erd.domain.mission.exception.code.MissionSuccessCode;
import org.example.erd.domain.mission.service.MissionService;
import org.example.erd.global.apiPayload.ApiResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MissionController {

    private final MissionService missionService;


    @Operation(summary = "내 미션 보기", description = "진행중, 완료 상태의 내 미션을 조회")
    @GetMapping("/members/{memberId}/missions")
    public ApiResponse<MissionResDTO.MissionListPageRes> getMyMissions(
            @PathVariable Long memberId, @RequestParam MissionStatus status,
            @PageableDefault(size=10)Pageable pageable
            ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_LIST_SUCCESS,
                missionService.getMyMissions(memberId,status,pageable));
    }

    @Operation(summary = "미션 완료 처리", description = "특정 미션의 상태를 완료로 변경")
    @PatchMapping("/missions/{memberMissionId}")
    public ApiResponse<MissionResDTO.MissionCompleteRes> completeMission(
            @PathVariable Long memberMissionId,
            @RequestHeader("Authorization") String authorization
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_COMPLETE_SUCCESS, null);
    }
}
