package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/missions")
public class MissionController {

    @PostMapping
    public ApiResponse<MissionResDTO.CreateMissionResponse> createMission(
            @RequestBody MissionReqDTO.CreateMissionRequest request
    ) {
        MissionResDTO.CreateMissionResponse response =
                MissionResDTO.CreateMissionResponse.builder()
                        .missionId(1L)
                        .title(request.title())
                        .point(request.point())
                        .restaurantId(request.restaurantId())
                        .build();

        return ApiResponse.onSuccess(GeneralSuccessCode.MISSION_CREATE_SUCCESS, response);
    }

    @PostMapping("/detail")
    public ApiResponse<MissionResDTO.GetMissionResponse> getMission(
            @RequestBody MissionReqDTO.GetMissionRequest request
    ) {
        MissionResDTO.GetMissionResponse response =
                MissionResDTO.GetMissionResponse.builder()
                        .missionId(request.missionId())
                        .title("음식점 리뷰 작성하기")
                        .content("해당 음식점에서 식사 후 리뷰를 작성하는 미션입니다.")
                        .point(500)
                        .status("진행 가능")
                        .build();

        return ApiResponse.onSuccess(GeneralSuccessCode.MISSION_GET_SUCCESS, response);
    }
}