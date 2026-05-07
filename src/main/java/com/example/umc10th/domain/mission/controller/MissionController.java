package com.example.umc10th.domain.mission.controller;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // 식당별 미션 목록
    @GetMapping("/restaurants/{restaurantId}/missions")
    public ApiResponse<Page<MissionResponseDto.MissionResult>> getMissions(
            @PathVariable Long restaurantId,
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Mission> missions =
                missionService.getMissionsByRestaurant(restaurantId, pageable);
        return ApiResponse.onSuccess(
                missions.map(MissionConverter::toMissionResult)
        );
    }

    // 내 미션 목록
    @GetMapping("/members/my/missions")
    public ApiResponse<Page<MissionResponseDto.MyMissionResult>> getMyMissions(
            @RequestParam MissionStatus status,
            @RequestParam Long userId,           
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserMission> userMissions =
                missionService.getMyMissions(userId, status, pageable);
        return ApiResponse.onSuccess(
                userMissions.map(MissionConverter::toMyMissionResult)
        );
    }

    // 미션 도전 시작
    @PostMapping("/missions/{missionId}/start")
    public ApiResponse<MissionResponseDto.UserMissionResult> startMission(
            @PathVariable Long missionId,
            @RequestParam Long userId
    ) {
        UserMission userMission = missionService.startMission(userId, missionId);
        return ApiResponse.onSuccess(
                MissionConverter.toUserMissionResult(userMission)
        );
    }

    // 미션 성공
    @PatchMapping("/user-missions/{userMissionId}/complete")
    public ApiResponse<MissionResponseDto.UserMissionResult> completeMission(
            @PathVariable Long userMissionId,
            @RequestParam Long userId
    ) {
        UserMission userMission = missionService.completeMission(userMissionId, userId);
        return ApiResponse.onSuccess(
                MissionConverter.toUserMissionResult(userMission)
        );
    }
}
