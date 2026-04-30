package org.example.erd.domain.review.dto;

import java.util.List;

public class ReviewReqDTO {

    public record CreateReview(
            Double reviewRating,
            String reviewContent,
            List<String> reviewImage
    ) {}
}
