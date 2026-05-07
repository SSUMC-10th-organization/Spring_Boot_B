package org.example.erd.domain.mission.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.erd.domain.mission.converter.MissionConverter;
import org.example.erd.domain.mission.dto.MissionResDTO;
import org.example.erd.domain.mission.entity.mapping.MemberMission;
import org.example.erd.domain.mission.enums.MissionStatus;
import org.example.erd.domain.mission.repository.MemberMissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;

    public MissionResDTO.MissionListPageRes getMyMissions(
            Long memberId,
            MissionStatus status,
            Pageable pageable

    )
    {
        Page<MemberMission> page = memberMissionRepository
                .findByMemberIdAndMissionStatus(memberId,status,pageable);

        return MissionConverter.toMissionListPageRes(page);
    }
}
