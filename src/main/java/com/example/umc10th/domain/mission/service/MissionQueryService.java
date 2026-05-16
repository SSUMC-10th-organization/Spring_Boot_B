package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.entity.MemberMission;
import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

public interface MissionQueryService {
    Page<MemberMission> getMemberMissions(Long memberId, Boolean isComplete, Integer page);
    Page<Mission> getMissionsByLocation(Long locationId, Integer page);
}
