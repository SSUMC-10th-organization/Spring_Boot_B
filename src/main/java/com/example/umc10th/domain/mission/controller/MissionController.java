package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.res.HomeResponseDTO;
import com.example.umc10th.domain.mission.dto.res.MissionResDTO;
import com.example.umc10th.domain.mission.dto.res.MissionUpdateResponseDTO;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.HomeService;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.domain.review.dto.req.ReviewMyReqDTO;
import com.example.umc10th.domain.review.dto.res.ReviewResDTO;
import com.example.umc10th.domain.review.enums.ReviewSortType;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseCode;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {
    private final MissionService missionService;
    private final HomeService homeService;
    private final ReviewService reviewService;

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

    // 가게 미션 생성
    @PostMapping("/stores/{storeId}/missions")
    public ApiResponse<Void> createMission(
        @PathVariable Long storeId,
        @RequestBody @Valid MissionReqDTO.Create dto
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.CREATED, missionService.createMission(storeId, dto));
    }

    // 가게 미션 조회
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.Summary>> getMission(
        @PathVariable Long storeId,
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(defaultValue = "0")  Integer pageNumber,
        @RequestParam(required = false)    String sort
    ){
        return ApiResponse.onSuccess(
            MissionSuccessCode.OK,
            missionService.getMissions(storeId, pageSize, pageNumber, sort ));
    }

    // 미션 도전
    @PostMapping("/missions/{missionId}/challenge")
    public ApiResponse<Long> challengeMission(
        @PathVariable Long missionId,
        @Valid @RequestBody MissionReqDTO.Challenge dto
    ){
        return ApiResponse.onSuccess(
            MissionSuccessCode.CHALLENGE_OK,
            missionService.challengeMission(missionId, dto.userId())
        );
    }

    // 진행 중인 미션 목록 조회
    @GetMapping("/users/mission/in-porgress")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.InProgressItem>> getInProgressMissions(
        @Valid @RequestBody MissionReqDTO.InProgress dto,
        @RequestParam Integer pageNumber,
        @RequestParam Integer pageSize
    ){
        return ApiResponse.onSuccess(
            MissionSuccessCode.IN_PROGRESS_OK,
            missionService.getInProgressMissions(dto.userId(), pageNumber, pageSize)
        );
    }

    // 내가 쓴 리뷰 조회 (커서 페이지네이션)
    @GetMapping("/users/reviews/my")
    public ApiResponse<ReviewResDTO.CursorPage<ReviewResDTO.MyReviewItem>> getMyReviews(
        @Valid @RequestBody ReviewMyReqDTO dto,
        @RequestParam(defaultValue = "ID") ReviewSortType sort,
        @RequestParam(required = false) Long cursorId,
        @RequestParam(required = false) Integer cursorRating,
        @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(
            ReviewSuccessCode.MY_REVIEWS_OK,
            reviewService.getMyReviews(dto.userId(), sort, cursorId, cursorRating, size)
        );
    }
}
