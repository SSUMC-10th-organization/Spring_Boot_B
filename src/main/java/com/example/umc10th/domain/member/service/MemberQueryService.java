package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.entity.Member;

public interface MemberQueryService {
    Member getMember(Long memberId);
}
