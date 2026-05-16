package com.example.coco.domain.user.service;

import com.example.coco.domain.mission.entity.Mission;
import com.example.coco.domain.mission.repository.MissionRepository;
import com.example.coco.domain.user.dto.UserResponseDTO;
import com.example.coco.domain.user.entity.Member;
import com.example.coco.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    public UserResponseDTO.MyPageDTO getMyPageData(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. id=" + memberId));

        return new UserResponseDTO.MyPageDTO(
                member.getProfileImage(),
                member.getNickname(),
                member.getEmail(),
                member.getPhoneNumber(),
                member.getPoint()
        );
    }

    public UserResponseDTO.HomeDTO getHomeData(Long memberId, Pageable pageable) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. id=" + memberId));

        Page<Mission> availableMissions = missionRepository.findAvailableMissionsByRegion(
                member.getRegion().getId(), memberId, pageable);

        List<UserResponseDTO.MissionDetailDTO> missionDetailDTOs = availableMissions.getContent().stream()
                .map(m -> new UserResponseDTO.MissionDetailDTO(
                        "mission_00" + m.getId(),
                        m.getStore().getName(),
                        m.getStore().getCategory(),
                        m.getMissionCondition(),
                        m.getRewardPoint(),
                        m.getDDay(),
                        m.getStatus()
                )).toList();

        UserResponseDTO.MissionProgressDTO progress = new UserResponseDTO.MissionProgressDTO(7, 10, 1000);

        return new UserResponseDTO.HomeDTO(member.getPoint(), progress, missionDetailDTOs);
    }
}