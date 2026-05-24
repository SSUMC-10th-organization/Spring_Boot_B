package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    public MissionResDTO.MyMissionList getMyMissions(MissionReqDTO.MyMissionRequest request, int page, int size) {

        Status status = Status.valueOf(request.getStatus());
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));

        Page<UserMission> result = userMissionRepository.findByUserIdAndStatus(
                request.getUserId(), status, pageRequest);

        List<MissionResDTO.MyMissionSummary> summaries = result.getContent().stream()
                .map(um -> MissionResDTO.MyMissionSummary.builder()
                        .userMissionId(um.getId())
                        .missionId(um.getMission().getId())
                        .restaurantName(um.getMission().getRestaurant().getName())
                        .category(um.getMission().getRestaurant().getCategory())
                        .rewardPoint(um.getMission().getPoint())
                        .deadline(um.getMission().getDeadlineSt())
                        .status(um.getStatus().name())
                        .build())
                .collect(Collectors.toList());

        return MissionResDTO.MyMissionList.builder()
                .missions(summaries)
                .currentPage(result.getNumber())
                .totalPage(result.getTotalPages())
                .totalCount(result.getTotalElements())
                .hasNext(result.hasNext())
                .build();
    }

    @Transactional
    public MissionResDTO.UserMission startMission(Long userId, Long missionId) {

        // 이미 도전 중인지 확인
        boolean alreadyInProgress = userMissionRepository.existsByUserIdAndMissionIdAndStatus(
                userId, missionId, Status.IN_PROGRESS);
        if (alreadyInProgress) {
            throw new IllegalStateException("이미 도전 중인 미션입니다.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));

        UserMission userMission = UserMission.builder()
                .user(user)
                .mission(mission)
                .status(Status.IN_PROGRESS)
                .startedAt(LocalDateTime.now())
                .build();
        userMissionRepository.save(userMission);

        return MissionResDTO.UserMission.builder()
                .userMissionId(userMission.getId())
                .status(userMission.getStatus().name())
                .startedAt(userMission.getStartedAt())
                .build();
    }

    @Transactional
    public MissionResDTO.UserMission cancelMission(Long userId, Long userMissionId) {

        UserMission userMission = userMissionRepository.findById(userMissionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));

        if (!userMission.getUser().getId().equals(userId)) {
            throw new IllegalStateException("본인의 미션만 취소할 수 있습니다.");
        }
        if (userMission.getStatus() != Status.IN_PROGRESS) {
            throw new IllegalStateException("진행 중인 미션만 취소할 수 있습니다.");
        }

        userMission.updateStatus(Status.CANCELED);

        return MissionResDTO.UserMission.builder()
                .userMissionId(userMission.getId())
                .status(userMission.getStatus().name())
                .startedAt(userMission.getStartedAt())
                .build();
    }
}