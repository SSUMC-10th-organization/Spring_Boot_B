package com.example.umc10th.domain.store.dto;

import lombok.Getter;

public class StoreRequestDTO {

    @Getter
    public static class ReviewDto {
        private Long memberId;
        private Float rating;
        private String content;
    }
}
