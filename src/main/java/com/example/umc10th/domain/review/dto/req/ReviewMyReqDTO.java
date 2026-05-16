package com.example.umc10th.domain.review.dto.req;

public record ReviewMyReqDTO(
    @NotNull(message = "userId 는 필수입니다")
    Long userId
) {}
