package com.example.coco.domain.review.service;

import com.example.coco.domain.review.dto.ReviewRequestDTO;
import com.example.coco.domain.review.entity.Review;
import com.example.coco.domain.review.repository.ReviewRepository;
import com.example.coco.domain.store.entity.Store;
import com.example.coco.domain.user.entity.Member;
import com.example.coco.domain.user.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final EntityManager em; // 가게 엔티티 조회를 위한 영속성 컨텍스트 주입

    // [리뷰 작성 프로세스 - 가용 데이터 검증 및 영속화]
    @Transactional
    public String createReview(Long storeId, Long memberId, ReviewRequestDTO.CreateReviewDTO dto) {
        // 1. 요청 유저 검증
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. id=" + memberId));

        // 2. 대상 대상 가게 조회
        Store store = em.find(Store.class, storeId);
        if (store == null) {
            throw new IllegalArgumentException("해당 가게가 존재하지 않습니다. id=" + storeId);
        }

        // 3. 엔티티 생성 및 양방향 세팅 후 저장 (사진 항목 제외 요구사항 반영)
        Review review = Review.builder()
                .rating(dto.rating())
                .content(dto.content())
                .member(member)
                .store(store)
                .build();

        reviewRepository.save(review);
        return "리뷰 등록 성공";
    }
}