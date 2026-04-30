package org.example.erd.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.example.erd.domain.mission.dto.MissionResDTO;
import org.example.erd.domain.mission.exception.code.MissionSuccessCode;
import org.example.erd.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MissionController {

    @GetMapping("/users/missions")
    public ApiResponse<MissionResDTO.MissionListRes> getMissions(
            @RequestHeader("Authorization") String authorization,
            @RequestParam String status
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_LIST_SUCCESS, null);
    }

    @PatchMapping("/missions/{memberMissionId}")
    public ApiResponse<MissionResDTO.MissionCompleteRes> completeMission(
            @PathVariable Long memberMissionId,
            @RequestHeader("Authorization") String authorization
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_COMPLETE_SUCCESS, null);
    }
}
