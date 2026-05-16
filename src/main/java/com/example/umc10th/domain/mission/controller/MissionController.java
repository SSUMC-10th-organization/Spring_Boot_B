package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.res.HomeResponseDTO;
import com.example.umc10th.domain.mission.dto.res.MissionListResponseDTO;
import com.example.umc10th.domain.mission.dto.res.MissionUpdateResponseDTO;
import com.example.umc10th.domain.mission.service.HomeService;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {
    private final MissionService missionService;
    private final HomeService homeService;

    @GetMapping("/mission")
    public ApiResponse<Page<MissionUpdateResponseDTO>> getMission(Pageable pageable){
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionService.getMission(pageable));
    }

    @PatchMapping("/mission/{missionId}")
    public ApiResponse<MissionUpdateResponseDTO> updateMission(
        @PathVariable Long missionId
    ){
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionService.updateMission());
    }

    @GetMapping("/home")
    public ApiResponse<HomeResponseDTO> getHome(@RequestParam String location)
    {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, homeService.getHome(location));
    }
}
