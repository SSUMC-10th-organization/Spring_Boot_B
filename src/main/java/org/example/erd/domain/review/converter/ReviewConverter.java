package org.example.erd.domain.review.converter;

import org.example.erd.domain.member.entity.Member;
import org.example.erd.domain.review.dto.ReviewReqDTO;
import org.example.erd.domain.review.dto.ReviewResDTO;
import org.example.erd.domain.review.entity.Review;
import org.example.erd.domain.store.entity.Store;

import java.util.List;

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

    public static ReviewResDTO.ReviewItem toReviewItem(Review review) {
        return ReviewResDTO.ReviewItem.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .score(review.getScore())
                .content(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static <T> ReviewResDTO.Pagination<T> toReviewPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
        return ReviewResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}
