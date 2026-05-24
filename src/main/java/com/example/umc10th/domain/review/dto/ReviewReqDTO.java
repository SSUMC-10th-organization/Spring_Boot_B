package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ReviewReqDTO {

    @Getter
    public static class Create {

        @NotNull
        private Long userMissionId;

        @NotNull
        @Min(1) @Max(5)
        private Float rate;

        private String content;             // nullable

        private List<MultipartFile> images; // nullable
    }
}