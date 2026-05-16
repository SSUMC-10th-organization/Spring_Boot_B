package org.example.erd.domain.review.converter;

import org.example.erd.domain.member.entity.Member;
import org.example.erd.domain.review.dto.ReviewReqDTO;
import org.example.erd.domain.review.dto.ReviewResDTO;
import org.example.erd.domain.review.entity.Review;
import org.example.erd.domain.store.entity.Store;

public class ReviewConverter {

    public static Review toReview(Member member, Store store, ReviewReqDTO.CreateReview dto) {
        return Review.builder()
                .member(member)
                .store(store)
                .score(dto.reviewRating().floatValue())
                .content(dto.reviewContent())
                .build();
    }


    public static ReviewResDTO.CreateReviewRes toCreateReviewRes(Review review){
        return ReviewResDTO.CreateReviewRes.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
