package org.example.erd.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.erd.domain.member.dto.MemberReqDTO;
import org.example.erd.domain.member.dto.MemberResDTO;
import org.example.erd.domain.member.exception.code.MemberSuccessCode;
import org.example.erd.domain.member.service.MemberService;
import org.example.erd.global.apiPayload.ApiResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "홈 화면 조회", description = "현재 지역의 도전 가능 미션 + 진행중 미션 + 미션 진행도 조회")
    @GetMapping("/{memberId}/home")
    public ApiResponse<MemberResDTO.HomeRes> getHome(
            @PathVariable Long memberId ,@RequestParam Long regionId,
            @PageableDefault(size = 10)Pageable pageable
            ) {
        return ApiResponse.onSuccess(MemberSuccessCode.HOME_SUCCESS,
                memberService.getHome(memberId,regionId,pageable));
    }

    @Operation(summary = "마이페이지 조회", description = "닉네임, 이메일, 핸드폰 번호, 포인트를 조회합니다.")
    @GetMapping("{memberId}/my-page")
    public ApiResponse<MemberResDTO.MyPageRes> getMyPage(
            @PathVariable Long memberId
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.MY_PAGE_SUCCESS,
                memberService.getMyPage(memberId)
        );
    }

}
