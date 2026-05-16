package com.example.coco.domain.review.service;

import com.example.coco.domain.review.dto.ReviewRequestDTO;
import com.example.coco.domain.review.dto.ReviewResponseDTO;
import com.example.coco.domain.review.entity.Review;
import com.example.coco.domain.review.repository.ReviewRepository;
import com.example.coco.domain.store.entity.Store;
import com.example.coco.domain.user.entity.Member;
import com.example.coco.domain.user.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final EntityManager em;

    @Transactional
    public String createReview(Long storeId, Long memberId, ReviewRequestDTO.CreateReviewDTO dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. id=" + memberId));

        Store store = em.find(Store.class, storeId);
        if (store == null) {
            throw new IllegalArgumentException("해당 가게가 존재하지 않습니다. id=" + storeId);
        }

        Review review = Review.builder()
                .rating(dto.rating())
                .content(dto.content())
                .member(member)
                .store(store)
                .build();

        reviewRepository.save(review);
        return "리뷰 등록 성공";
    }

    public ReviewResponseDTO.ReviewCursorPageDTO getMyReviewsCursor(Long memberId, Long cursorId, Float cursorRating, String sortBy, int size) {
        PageRequest pageRequest = PageRequest.of(0, size + 1);
        List<Review> reviewList;

        if ("rating".equals(sortBy)) {
            reviewList = reviewRepository.findMyReviewsCursorByRating(memberId, cursorRating, cursorId, pageRequest);
        } else {
            reviewList = reviewRepository.findMyReviewsCursorById(memberId, cursorId, pageRequest);
        }

        boolean hasNext = reviewList.size() > size;
        if (hasNext) {
            reviewList = reviewList.subList(0, size);
        }

        List<ReviewResponseDTO.ReviewDetailDTO> dtos = reviewList.stream()
                .map(r -> new ReviewResponseDTO.ReviewDetailDTO(
                        r.getId(),
                        r.getMember().getNickname(),
                        r.getRating().floatValue(),
                        r.getContent(),
                        "2026-05-16",
                        "감사합니다."
                )).toList();

        Long nextCursor = reviewList.isEmpty() ? null : reviewList.get(reviewList.size() - 1).getId();

        return new ReviewResponseDTO.ReviewCursorPageDTO(dtos, nextCursor, hasNext);
    }
}