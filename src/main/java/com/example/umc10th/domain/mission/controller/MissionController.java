package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
public class MissionController {

    @PostMapping("/{missionId}/challenges")
    public ApiResponse<MissionResponseDTO.ChallengeResultDto> challengeMission(
            @PathVariable Long missionId,
            @RequestBody MissionRequestDTO.ChallengeDto request) {
        
        // 더미 로직: 실제 DB 저장 없이 임의의 매핑 ID와 현재 시간을 반환
        return ApiResponse.onSuccess(MissionConverter.toChallengeResultDto(1L));
    }
}
