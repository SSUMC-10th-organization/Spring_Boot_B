package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDto> join(@RequestBody MemberRequestDTO.JoinDto request) {
        // 더미 로직: 실제 DB 저장 없이 임의의 회원 ID와 현재 시간을 반환
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDto(1L));
    }
}
