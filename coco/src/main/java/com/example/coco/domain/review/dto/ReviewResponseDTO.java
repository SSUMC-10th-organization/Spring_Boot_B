package com.example.coco.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class ReviewResponseDTO {

    public record ReviewDetailDTO(
            Long reviewId,
            String nickname,
            Float rating,
            String content,
            String createdAt,
            String ownerReply
    ) {}

    public record ReviewCursorPageDTO(
            List<ReviewDetailDTO> reviews,
            @Schema(description = "다음 스크롤 시 사용할 마지막 커서 ID (없으면 null)", example = "15")
            Long nextCursor,
            @Schema(description = "다음 페이지 존재 여부")
            Boolean hasNext
    ) {}
}