package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import java.time.LocalDateTime;

public class MissionConverter {

    public static MissionResponseDTO.ChallengeResultDto toChallengeResultDto(Long memberMissionId) {
        return MissionResponseDTO.ChallengeResultDto.builder()
                .memberMissionId(memberMissionId)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
