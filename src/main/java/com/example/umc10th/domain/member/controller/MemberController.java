package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.service.MemberQueryService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Validated
@Tag(name = "Member API", description = "회원 관련 API")
public class MemberController {

    private final MemberQueryService memberQueryService;

    @PostMapping("/")
    @Operation(summary = "회원 가입 API", description = "새로운 회원을 등록하는 API입니다.")
    public ApiResponse<MemberResponseDTO.JoinResultDto> join(@Valid @RequestBody MemberRequestDTO.JoinDto request) {
        // 더미 로직 유지
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDto(1L));
    }

    @GetMapping("/{memberId}/mypage")
    @Operation(summary = "마이페이지 조회 API", description = "특정 회원의 마이페이지 정보를 조회하는 API입니다.")
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable 입니다.")
    })
    public ApiResponse<MemberResponseDTO.MyPageDto> getMyPage(@PathVariable Long memberId) {
        Member member = memberQueryService.getMember(memberId);
        return ApiResponse.onSuccess(MemberConverter.toMyPageDto(member));
    }
}
