package org.example.erd.domain.review.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.erd.global.apiPayload.code.BaseSuccessCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATE_SUCCESS(HttpStatus.CREATED, "REVIEW201_1", "리뷰 작성이 완료되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
