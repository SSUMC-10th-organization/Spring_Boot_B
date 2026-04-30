package com.example.coco.base;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "공통 응답 객체")
public record ApiResponse<T>(
        @Schema(description = "성공 여부", example = "true")
        Boolean isSuccess,
        @Schema(description = "응답 코드", example = "COMMON200")
        String code,
        @Schema(description = "응답 메시지", example = "요청에 성공하였습니다.")
        String message,
        @Schema(description = "응답 데이터")
        T result
) {
    public static <T> ApiResponse<T> onSuccess(T result) {
        return new ApiResponse<>(true, "COMMON200", "요청에 성공하였습니다.", result);
    }

    public static <T> ApiResponse<T> onFailure(String code, String message, T data) {
        return new ApiResponse<>(false, code, message, data);
    }
}