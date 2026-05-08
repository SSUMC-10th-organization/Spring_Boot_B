package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.MemberMission;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.store.entity.Location;
import com.example.umc10th.domain.store.repository.LocationRepository;
import com.example.umc10th.global.apiPayload.code.status.ErrorStatus;
import com.example.umc10th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final LocationRepository locationRepository;

    @Override
    public Page<MemberMission> getMemberMissions(Long memberId, Boolean isComplete, Integer page) {
        
        PageRequest pageRequest = PageRequest.of(page, 10);
        return memberMissionRepository.findAllByMemberIdAndComplete(memberId, isComplete, pageRequest);
    }

    @Override
    public Page<Mission> getMissionsByLocation(Long locationId, Integer page) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.LOCATION_NOT_FOUND));
        
        PageRequest pageRequest = PageRequest.of(page, 10);
        return missionRepository.findAllByStore_Location(location, pageRequest);
    }
}
