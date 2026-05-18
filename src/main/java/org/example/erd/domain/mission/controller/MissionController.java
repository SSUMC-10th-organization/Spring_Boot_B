package org.example.erd.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.erd.domain.mission.dto.MissionReqDTO;
import org.example.erd.domain.mission.dto.MissionResDTO;
import org.example.erd.domain.mission.enums.MissionStatus;
import org.example.erd.domain.mission.exception.code.MissionSuccessCode;
import org.example.erd.domain.mission.service.MissionService;
import org.example.erd.global.apiPayload.ApiResponse;
import org.example.erd.global.apiPayload.code.BaseSuccessCode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MissionController {

    private final MissionService missionService;


    @Operation(summary = "내 미션 전체 보기", description = "진행중, 완료 상태의 내 미션을 조회")
    @GetMapping("/members/missions")
    public ApiResponse<MissionResDTO.MissionListPageRes> getMyMissions(
            @RequestBody MissionReqDTO.GetMyMissions dto,
            @PageableDefault(size = 10) Pageable pageable
            ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_LIST_SUCCESS,
                missionService.getMyMissions(dto.memberId(),dto.status(),pageable));
    }

    @Operation(summary = "내 진행중인 미션 보기", description = "진행중인 내 미션을 조회")
    @GetMapping("/members/missions/challenging")
    public ApiResponse<MissionResDTO.MissionListPageRes> getChallengingMissions(
            @RequestBody MissionReqDTO.GetChallengingMissions dto,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_LIST_SUCCESS,
                missionService.getMyMissions(dto.memberId(),MissionStatus.CHALLENGING,pageable));
    }


    @Operation(summary = "미션 완료 처리", description = "특정 미션의 상태를 완료로 변경")
    @PatchMapping("/missions/{memberMissionId}")
    public ApiResponse<MissionResDTO.MissionCompleteRes> completeMission(
            @PathVariable Long memberMissionId,
            @RequestHeader("Authorization") String authorization
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_COMPLETE_SUCCESS, null);
    }

    //가게 미션 생성
    @PostMapping("/stores/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @Valid @RequestBody MissionReqDTO.CreateMission dto
            ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_CREATED_SUCCESS;
        return ApiResponse.onSuccess(code,missionService.createMission(storeId,dto));
    }

    // 가게 내 미션들 조회
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    )
    {
        BaseSuccessCode code = MissionSuccessCode.STORE_MISSION_LIST_SUCCESS;
        return ApiResponse.onSuccess(code,missionService.getMissions(storeId,pageSize,cursor,query));
    }
}
