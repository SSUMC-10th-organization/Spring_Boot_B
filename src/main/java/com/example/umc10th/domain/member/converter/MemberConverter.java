package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import java.time.LocalDateTime;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDto toJoinResultDto(Long memberId) {
        return MemberResponseDTO.JoinResultDto.builder()
                .memberId(memberId)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
