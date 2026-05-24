package com.example.umc10th.domain.home.service;

import com.example.umc10th.domain.home.dto.HomeResDTO;
import com.example.umc10th.domain.home.entity.Location;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.home.repository.LocationRepository;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeService {

    private final LocationRepository locationRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    private static final int TOTAL_MISSION_COUNT = 10;

    public HomeResDTO.Home getHome(Long userId, Long locationId) {

        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 지역입니다."));

        // 지역별 완료 미션 수
        int clearedCount = userMissionRepository.countCompletedByUserIdAndLocationId(userId, locationId);

        // 미션 목록 (최근 10개)
        Slice<Mission> missions = missionRepository.findByLocationId(locationId, PageRequest.of(0, TOTAL_MISSION_COUNT));

        List<HomeResDTO.MissionSummary> missionSummaries = missions.getContent().stream()
                .map(m -> HomeResDTO.MissionSummary.builder()
                        .missionId(m.getId())
                        .restaurantName(m.getRestaurant().getName())
                        .category(m.getRestaurant().getCategory())
                        .rewardPoint(m.getPoint())
                        .deadline(m.getDeadlineSt())
                        .status(Status.IN_PROGRESS.name())
                        .build())
                .collect(Collectors.toList());

        return HomeResDTO.Home.builder()
                .locationName(location.getName())
                .clearedCount(clearedCount)
                .totalCount(TOTAL_MISSION_COUNT)
                .missions(missionSummaries)
                .build();
    }
}
