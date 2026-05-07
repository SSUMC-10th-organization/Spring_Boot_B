package com.example.umc10th.global.apiPayload.code.status;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    
    // Member Error
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "존재하지 않는 회원입니다."),
    
    // Store Error
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404", "존재하지 않는 가게입니다."),
    
    // Location Error
    LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND, "LOCATION404", "존재하지 않는 지역입니다."),
    
    // Mission Error
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404", "존재하지 않는 미션입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
