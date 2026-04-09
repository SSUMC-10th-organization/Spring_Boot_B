package com.example.umc10th.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Member API", description = "사용자 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    @GetMapping("/test")
    @Operation(summary = "멤버 테스트 API", description = "서버 연결 확인용 테스트 API입니다.")
    public String testAPI() {
        return "Member API Status: OK";
    }
}