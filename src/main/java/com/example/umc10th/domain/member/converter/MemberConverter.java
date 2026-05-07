package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.entity.Member;

import java.time.LocalDateTime;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDto toJoinResultDto(Long memberId) {
        return MemberResponseDTO.JoinResultDto.builder()
                .memberId(memberId)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MemberResponseDTO.MyPageDto toMyPageDto(Member member) {
        return MemberResponseDTO.MyPageDto.builder()
                .memberId(member.getId())
                .nickname(member.getName()) // Use getName() instead of getNickname()
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }
}
