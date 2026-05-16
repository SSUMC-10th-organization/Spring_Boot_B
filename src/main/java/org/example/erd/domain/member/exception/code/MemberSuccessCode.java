package org.example.erd.domain.member.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.erd.global.apiPayload.code.BaseSuccessCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {


    HOME_SUCCESS(HttpStatus.OK, "MEMBER200_1", "홈 화면 조회에 성공했습니다."),
    MY_PAGE_SUCCESS(HttpStatus.OK,"MEMBER200_2","마이페이지 조회 성공"),
    ;


    private final HttpStatus status;
    private final String code;
    private final String message;
}
