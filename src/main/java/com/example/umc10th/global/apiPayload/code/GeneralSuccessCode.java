package com.example.umc10th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    USER_MY_PAGE_SUCCESS(HttpStatus.OK, "USER200_1", "성공적으로 유저를 조회했습니다."),

    MISSION_CREATE_SUCCESS(HttpStatus.OK, "MISSION200_1", "성공적으로 미션을 생성했습니다."),
    MISSION_GET_SUCCESS(HttpStatus.OK, "MISSION200_2", "성공적으로 미션을 조회했습니다."),

    RESTAURANT_CREATE_SUCCESS(HttpStatus.OK, "RESTAURANT200_1", "성공적으로 식당을 생성했습니다."),
    RESTAURANT_GET_SUCCESS(HttpStatus.OK, "RESTAURANT200_2", "성공적으로 식당을 조회했습니다."),

    REVIEW_CREATE_SUCCESS(HttpStatus.OK, "REVIEW200_1", "성공적으로 리뷰를 생성했습니다."),
    REVIEW_GET_SUCCESS(HttpStatus.OK, "REVIEW200_2", "성공적으로 리뷰를 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}