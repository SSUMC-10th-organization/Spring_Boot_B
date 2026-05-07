package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.service.MemberQueryService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberQueryService memberQueryService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDto> join(@RequestBody MemberRequestDTO.JoinDto request) {
        // 더미 로직 유지
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDto(1L));
    }

    @GetMapping("/{memberId}/mypage")
    public ApiResponse<MemberResponseDTO.MyPageDto> getMyPage(@PathVariable Long memberId) {
        Member member = memberQueryService.getMember(memberId);
        return ApiResponse.onSuccess(MemberConverter.toMyPageDto(member));
    }
}
