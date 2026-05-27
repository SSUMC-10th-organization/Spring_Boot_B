package org.example.erd.domain.member.converter;

import org.example.erd.domain.member.dto.MemberResDTO;
import org.example.erd.domain.member.entity.Member;
import org.example.erd.domain.mission.entity.Mission;
import org.example.erd.domain.mission.entity.mapping.MemberMission;
import org.example.erd.global.security.dto.OAuthDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class MemberConverter {

    public static MemberResDTO.MissionInfo toMissionInfo(Mission mission) {
        return MemberResDTO.MissionInfo.builder()
                .deadline(mission.getDeadline())
                .storeName(mission.getStore().getName())

                .storeCategory(mission.getStore().getFood().getFoodType().name())
                .missionContent(mission.getConditional())
                .point(mission.getPoint())
                .build();
    }


    public static MemberResDTO.HomeRes toHomeRes(
            Member member,
            String regionName,
            long missionSuccessCount,
            long missionTotalCount,
            List<MemberMission> challengingList,
            Page<Mission> availalblePage
    ){
        List<MemberResDTO.MissionInfo> challenging = challengingList.stream()
                .map(mm -> toMissionInfo(mm.getMission()))
                .toList();

        List<MemberResDTO.MissionInfo> available = availalblePage.getContent().stream()
                .map(MemberConverter::toMissionInfo)
                .toList();


        return MemberResDTO.HomeRes.builder()
                .name(member.getName())
                .region(regionName)
                .missionSuccessCount(missionSuccessCount)
                .missionTotalCount(missionTotalCount)
                .rewardPoint(1000)
                .challengingMissions(challenging)
                .availableMissions(available)
                .hasNext(availalblePage.hasNext())
                .build();
    }

    public static Member toMember(OAuthDTO dto) {
        return Member.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(UUID.randomUUID().toString())
                .phoneNumber("")
                .birth(LocalDate.now())
                .address("")
                .detailAddress("")
                .point(0)
                .socialType(dto.getSocialType())
                .socialUid(dto.getSocialUid())
                .build();
    }

    public static MemberResDTO.Login toLogin(String accessToken) {
        return MemberResDTO.Login.builder()
                .accessToken(accessToken)
                .build();
    }

    public static MemberResDTO.MyPageRes toMyPageRes(Member member) {
        return MemberResDTO.MyPageRes.builder()
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }
}
