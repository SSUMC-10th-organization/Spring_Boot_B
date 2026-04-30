package org.example.erd.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.example.erd.domain.member.dto.MemberReqDTO;
import org.example.erd.domain.member.dto.MemberResDTO;
import org.example.erd.domain.member.exception.code.MemberSuccessCode;
import org.example.erd.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {



    @PostMapping("/member/signup")
    public ApiResponse<MemberResDTO.SignUpRes> signup(
            @RequestBody MemberReqDTO.SignUp dto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.SIGNUP_SUCCESS, null);
    }
}
