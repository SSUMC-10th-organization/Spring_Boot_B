package com.example.coco.domain.mission.service;

import com.example.coco.domain.mission.dto.MissionRequestDTO;
import com.example.coco.domain.mission.dto.MissionResponseDTO;
import com.example.coco.domain.mission.entity.MemberMission;
import com.example.coco.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;

    public MissionResponseDTO.MissionListDTO getMyMissionsOffset(MissionRequestDTO.MyMissionOptsDTO dto, String status) {
        PageRequest pageRequest = PageRequest.of(dto.page(), 10);
        Page<MemberMission> pageResult = memberMissionRepository.findMyMissionsOffset(dto.memberId(), status, pageRequest);

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