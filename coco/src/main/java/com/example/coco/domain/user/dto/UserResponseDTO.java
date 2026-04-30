package com.example.coco.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class UserResponseDTO {

    public record HomeDTO(
            @Schema(description = "유저 포인트", example = "999999") Integer point,
            MissionProgressDTO missionProgress,
            List<MissionDetailDTO> missions
    ) {}

    public record MissionProgressDTO(
            @Schema(description = "진행률 분자", example = "7") Integer current,
            @Schema(description = "진행률 분모", example = "10") Integer total,
            @Schema(description = "성공 보상", example = "1000") Integer rewardPoint
    ) {}

    public record MissionDetailDTO(
            @Schema(description = "미션 ID", example = "mission_001") String id,
            @Schema(description = "가게명", example = "반이학생마라탕") String restaurantName,
            @Schema(description = "카테고리", example = "중식") String category,
            @Schema(description = "조건", example = "10,000원 이상 식사") String condition,
            @Schema(description = "보상", example = "500") Integer rewardPoint,
            @Schema(description = "D-Day", example = "7") Integer dDay,
            @Schema(description = "상태", example = "active") String status
    ) {}

    public record MyPageDTO(
            @Schema(description = "이미지 경로") String profileImage,
            @Schema(description = "닉네임", example = "nickname012") String nickname,
            @Schema(description = "이메일", example = "dlapdlf@naver.com") String email,
            @Schema(description = "전화번호") String phoneNumber,
            @Schema(description = "포인트", example = "2500") Integer point
    ) {}
}