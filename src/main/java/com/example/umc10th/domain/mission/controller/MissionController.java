package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.entity.MemberMission;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.service.MissionQueryService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionQueryService missionQueryService;

    @PostMapping("/{missionId}/challenges")
    public ApiResponse<MissionResponseDTO.ChallengeResultDto> challengeMission(
            @PathVariable Long missionId,
            @RequestBody MissionRequestDTO.ChallengeDto request) {
        
        // 더미 로직: 실제 DB 저장 없이 임의의 매핑 ID 반환 (도전 기능 자체는 쿼리/커맨드 복잡하므로 임시유지)
        return ApiResponse.onSuccess(MissionConverter.toChallengeResultDto(1L));
    }

    @GetMapping("/members/{memberId}")
    public ApiResponse<MissionResponseDTO.MemberMissionListDto> getMemberMissions(
            @PathVariable Long memberId,
            @RequestParam(name = "isComplete") Boolean isComplete,
            @RequestParam(name = "page", defaultValue = "0") Integer page) {
        
        Page<MemberMission> missionPage = missionQueryService.getMemberMissions(memberId, isComplete, page);
        return ApiResponse.onSuccess(MissionConverter.toMemberMissionListDto(missionPage));
    }

    @GetMapping("/locations/{locationId}")
    public ApiResponse<MissionResponseDTO.MissionListDto> getMissionsByLocation(
            @PathVariable Long locationId,
            @RequestParam(name = "page", defaultValue = "0") Integer page) {
        
        Page<Mission> missionPage = missionQueryService.getMissionsByLocation(locationId, page);
        return ApiResponse.onSuccess(MissionConverter.toMissionListDto(missionPage));
    }
}
