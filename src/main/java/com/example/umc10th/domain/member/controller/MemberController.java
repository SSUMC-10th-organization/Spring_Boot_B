package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.service.MemberQueryService;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.security.entity.AuthMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Validated
@Tag(name = "Member API", description = "회원 관련 API")
public class MemberController {

    private final MemberService memberService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/")
    @Operation(summary = "회원 가입 API", description = "새로운 회원을 등록하는 API입니다.")
    public ApiResponse<MemberResponseDTO.JoinResultDto> join(@Valid @RequestBody MemberRequestDTO.JoinDto request) {
        Member newMember = memberService.join(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDto(newMember.getId()));
    }

    @GetMapping("/mypage")
    @Operation(
        summary = "마이페이지 조회 API",
        description = "JWT 토큰으로 인증된 본인의 마이페이지 정보를 조회합니다.",
        security = @SecurityRequirement(name = "JWT TOKEN")
    )
    public ApiResponse<MemberResponseDTO.MyPageDto> getMyPage(@AuthenticationPrincipal AuthMember authMember) {
        Member member = authMember.getMember();
        return ApiResponse.onSuccess(MemberConverter.toMyPageDto(member));
    }
}
