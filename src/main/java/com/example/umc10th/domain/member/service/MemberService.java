package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.entity.Member;

public interface MemberService {
    Member join(MemberRequestDTO.JoinDto request);
}
