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

    // [마이페이지 조회]
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

    // [홈 화면 조회 - 현재 선택된 지역에서 도전 가능한 미션 목록 포함 (페이징)]
    public UserResponseDTO.HomeDTO getHomeData(Long memberId, Pageable pageable) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. id=" + memberId));

        // Repository의 @Query 호출 (지역 ID와 내 유저 ID 기준 필터링)
        Page<Mission> availableMissions = missionRepository.findAvailableMissionsByRegion(
                member.getRegion().getId(), memberId, pageable);

        // 엔티티 -> DTO 변환 목록 조립
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

        // 상단 미션 진행률 정보 조립 (기획서 예시 고정치 바인딩)
        UserResponseDTO.MissionProgressDTO progress = new UserResponseDTO.MissionProgressDTO(7, 10, 1000);

        return new UserResponseDTO.HomeDTO(member.getPoint(), progress, missionDetailDTOs);
    }
}