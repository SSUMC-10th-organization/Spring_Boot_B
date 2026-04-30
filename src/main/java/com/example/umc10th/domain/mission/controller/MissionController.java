package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.res.MissionListResponseDTO;
import com.example.umc10th.domain.mission.dto.res.MissionUpdateResponseDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {
    private final MissionService missionService;

    @GetMapping("/mission")
    public ApiResponse<MissionUpdateResponseDTO> getMission(){
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionService.getMission());
    }

    @PatchMapping("/mission/{missionId}")
    public ApiResponse<MissionUpdateResponseDTO> updateMission(
        @PathVariable Long missionId
    ){
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionService.updateMission());
    }
}
