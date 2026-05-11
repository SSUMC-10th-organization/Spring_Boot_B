package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.entity.MemberMission;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.service.MissionQueryService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.example.umc10th.validation.annotation.CheckPage;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
@Validated
@Tag(name = "Mission API", description = "미션 관련 API")
public class MissionController {

    private final MissionQueryService missionQueryService;

    @PostMapping("/{missionId}/challenges")
    @Operation(summary = "미션 도전하기 API", description = "특정 미션에 도전하는 API입니다.")
    @Parameters({
            @Parameter(name = "missionId", description = "미션의 아이디, path variable 입니다.")
    })
    public ApiResponse<MissionResponseDTO.ChallengeResultDto> challengeMission(
            @PathVariable Long missionId,
            @Valid @RequestBody MissionRequestDTO.ChallengeDto request) {
        
        return ApiResponse.onSuccess(MissionConverter.toChallengeResultDto(1L));
    }

    @PostMapping("/members/me")
    @Operation(summary = "내가 진행중인 미션 조회 API", description = "나의 진행중/진행완료 미션 목록을 조회하는 API입니다. 페이징을 포함합니다. 사용자 ID는 RequestBody로 받습니다.")
    @Parameters({
            @Parameter(name = "isComplete", description = "미션 완료 여부 (true: 완료, false: 진행중), query string 입니다."),
            @Parameter(name = "page", description = "페이지 번호 (0부터 시작), query string 입니다.")
    })
    public ApiResponse<MissionResponseDTO.MemberMissionListDto> getMyMissions(
            @Valid @RequestBody MissionRequestDTO.GetMyMissionsDto request,
            @RequestParam(name = "isComplete") Boolean isComplete,
            @CheckPage @RequestParam(name = "page", defaultValue = "0") Integer page) {
        
        Page<MemberMission> missionPage = missionQueryService.getMemberMissions(request.getMemberId(), isComplete, page);
        return ApiResponse.onSuccess(MissionConverter.toMemberMissionListDto(missionPage));
    }

    @GetMapping("/members/{memberId}")
    @Operation(summary = "사용자 미션 조회 API", description = "특정 사용자의 진행중/진행완료 미션 목록을 조회하는 API입니다. 페이징을 포함합니다.")
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable 입니다."),
            @Parameter(name = "isComplete", description = "미션 완료 여부 (true: 완료, false: 진행중), query string 입니다."),
            @Parameter(name = "page", description = "페이지 번호 (0부터 시작), query string 입니다.")
    })
    public ApiResponse<MissionResponseDTO.MemberMissionListDto> getMemberMissions(
            @PathVariable Long memberId,
            @RequestParam(name = "isComplete") Boolean isComplete,
            @CheckPage @RequestParam(name = "page", defaultValue = "0") Integer page) {
        
        Page<MemberMission> missionPage = missionQueryService.getMemberMissions(memberId, isComplete, page);
        return ApiResponse.onSuccess(MissionConverter.toMemberMissionListDto(missionPage));
    }

    @GetMapping("/locations/{locationId}")
    @Operation(summary = "지역별 미션 조회 API", description = "특정 지역의 도전 가능한 미션 목록을 조회하는 API입니다. 페이징을 포함합니다.")
    @Parameters({
            @Parameter(name = "locationId", description = "지역의 아이디, path variable 입니다."),
            @Parameter(name = "page", description = "페이지 번호 (0부터 시작), query string 입니다.")
    })
    public ApiResponse<MissionResponseDTO.MissionListDto> getMissionsByLocation(
            @PathVariable Long locationId,
            @CheckPage @RequestParam(name = "page", defaultValue = "0") Integer page) {
        
        Page<Mission> missionPage = missionQueryService.getMissionsByLocation(locationId, page);
        return ApiResponse.onSuccess(MissionConverter.toMissionListDto(missionPage));
    }
}
