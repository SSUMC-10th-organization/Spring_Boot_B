package com.example.umc10th.domain.store.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.store.dto.StoreRequestDTO;
import com.example.umc10th.domain.store.entity.Review;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.repository.ReviewRepository;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.global.apiPayload.code.status.ErrorStatus;
import com.example.umc10th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    public Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewDto request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
        
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));

        Review review = Review.builder()
                .member(member)
                .store(store)
                .content(request.getContent())
                .star(request.getRating())
                .build();

        return reviewRepository.save(review);
    }
}
