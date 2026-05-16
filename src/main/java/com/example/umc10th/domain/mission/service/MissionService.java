package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.res.MissionUpdateResponseDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

  private final MissionRepository missionRepository;

  public Page<MissionUpdateResponseDTO> getMission(Pageable pageable) {
    Page<Mission> missions = missionRepository.findAllWithStore(pageable);
    return missions.map(MissionConverter::toMissionUpdateResponse);
  }

  public MissionUpdateResponseDTO updateMission() {
    return null;
  }
}
