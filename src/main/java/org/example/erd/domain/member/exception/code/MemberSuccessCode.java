package org.example.erd.domain.member.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.erd.global.apiPayload.code.BaseSuccessCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    SIGNUP_SUCCESS(HttpStatus.CREATED, "MEMBER201_1", "회원가입이 완료되었습니다."),
    HOME_SUCCESS(HttpStatus.OK, "MEMBER200_1", "홈 화면 조회에 성공했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
