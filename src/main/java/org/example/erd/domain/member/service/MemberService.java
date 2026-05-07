package org.example.erd.domain.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.erd.domain.member.converter.MemberConverter;
import org.example.erd.domain.member.dto.MemberReqDTO;
import org.example.erd.domain.member.dto.MemberResDTO;
import org.example.erd.domain.member.entity.Member;
import org.example.erd.domain.member.exception.MemberException;
import org.example.erd.domain.member.exception.code.MemberErrorCode;
import org.example.erd.domain.member.repository.MemberRepository;
import org.example.erd.domain.mission.entity.Mission;
import org.example.erd.domain.mission.entity.mapping.MemberMission;
import org.example.erd.domain.mission.enums.MissionStatus;
import org.example.erd.domain.mission.repository.MemberMissionRepository;
import org.example.erd.domain.mission.repository.MissionRepository;
import org.example.erd.domain.store.entity.Region;
import org.example.erd.domain.store.repository.RegionRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final RegionRepository regionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    public MemberResDTO.MyPageRes getMyPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toMyPageRes(member);

    }

    public MemberResDTO.HomeRes getHome(Long memberId, Long regionId, Pageable pageable){
        // 멤버 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Region region = regionRepository.findById(regionId)
                .orElseThrow();

        long completeCount = memberMissionRepository.countByMemberIdAndMissionStatus(memberId, MissionStatus.COMPLETE);

        long totalCount = memberMissionRepository.countByMemberId(memberId);

        List<MemberMission> challengingList = memberMissionRepository.findByMemberIdAndMissionStatus(memberId,MissionStatus.CHALLENGING);

        List<Long> excludedIds = memberMissionRepository.findMissionIdsByMemberId(memberId);
        if (excludedIds.isEmpty()) excludedIds = List.of(-1L);

        Page<Mission> availablePage = missionRepository.findAvailableMissions(regionId,excludedIds,pageable);

        return MemberConverter.toHomeRes(
                member,region.getName(),completeCount,totalCount,challengingList,availablePage
        );


    }







}
