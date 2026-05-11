package com.example.umc10th.domain.store.service;

import com.example.umc10th.domain.store.dto.StoreResponseDTO;
import com.example.umc10th.domain.store.entity.Review;
import com.example.umc10th.domain.store.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    public StoreResponseDTO.ReviewListDto getMyReviews(Long memberId, String cursor, String sortBy, int size) {
        Pageable pageable = PageRequest.of(0, size + 1);
        List<Review> reviews;

        if ("star".equals(sortBy)) {
            if (cursor == null || cursor.isEmpty()) {
                reviews = reviewRepository.findByMemberIdOrderByStarDescIdDesc(memberId, pageable);
            } else {
                String[] cursorParts = cursor.split("_");
                Float cursorStar = Float.parseFloat(cursorParts[0]);
                Long cursorId = Long.parseLong(cursorParts[1]);
                reviews = reviewRepository.findByMemberIdAndStarLessThanOrderByStarDescIdDesc(memberId, cursorStar, cursorId, pageable);
            }
        } else {
            // default: by id
            if (cursor == null || cursor.isEmpty()) {
                reviews = reviewRepository.findByMemberIdOrderByIdDesc(memberId, pageable);
            } else {
                Long cursorId = Long.parseLong(cursor);
                reviews = reviewRepository.findByMemberIdAndIdLessThanOrderByIdDesc(memberId, cursorId, pageable);
            }
        }

        boolean hasNext = false;
        if (reviews.size() > size) {
            hasNext = true;
            reviews.remove(size);
        }

        String nextCursor = null;
        if (!reviews.isEmpty() && hasNext) {
            Review lastReview = reviews.get(reviews.size() - 1);
            if ("star".equals(sortBy)) {
                nextCursor = lastReview.getStar() + "_" + lastReview.getId();
            } else {
                nextCursor = String.valueOf(lastReview.getId());
            }
        }

        List<StoreResponseDTO.ReviewDto> reviewDtoList = reviews.stream()
                .map(review -> StoreResponseDTO.ReviewDto.builder()
                        .reviewId(review.getId())
                        .storeName(review.getStore().getName())
                        .memberId(review.getMember().getId())
                        .star(review.getStar())
                        .content(review.getContent())
                        .createdAt(review.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        return StoreResponseDTO.ReviewListDto.builder()
                .reviewList(reviewDtoList)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }
}
