package com.example.coco.domain.mission.service;

import com.example.coco.domain.mission.dto.MissionResponseDTO;
import com.example.coco.domain.mission.entity.MemberMission;
import com.example.coco.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;

    // [나의 미션 목록 상태별 조회 - 페이징 처리 및 DTO 파싱]
    public MissionResponseDTO.MissionListDTO getMyMissions(Long memberId, String status, Pageable pageable) {
        // Repository의 페이징 @Query를 수행하여 데이터 가져오기
        Page<MemberMission> pageResult = memberMissionRepository.findMyMissionsByStatus(memberId, status, pageable);

        // MemberMission 엔티티 리스트를 MissionDetailDTO 규격으로 매핑
        List<MissionResponseDTO.MissionDetailDTO> detailDTOs = pageResult.getContent().stream()
                .map(mm -> new MissionResponseDTO.MissionDetailDTO(
                        mm.getMission().getId(),
                        mm.getMission().getStore().getName(),
                        mm.getMission().getMissionCondition(),
                        mm.getMission().getRewardPoint(),
                        mm.getMission().getDDay(),
                        mm.getStatus()
                )).toList();

        return new MissionResponseDTO.MissionListDTO(detailDTOs);
    }
}