package org.example.erd.domain.review.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.erd.domain.member.entity.Member;
import org.example.erd.domain.member.exception.MemberException;
import org.example.erd.domain.member.exception.code.MemberErrorCode;
import org.example.erd.domain.member.repository.MemberRepository;
import org.example.erd.domain.review.converter.ReviewConverter;
import org.example.erd.domain.review.dto.ReviewReqDTO;
import org.example.erd.domain.review.dto.ReviewResDTO;
import org.example.erd.domain.review.entity.Review;
import org.example.erd.domain.review.repository.ReviewPhotoRepository;
import org.example.erd.domain.review.repository.ReviewRepository;
import org.example.erd.domain.store.entity.Store;
import org.example.erd.domain.store.exception.StoreException;
import org.example.erd.domain.store.exception.code.StoreErrorCode;
import org.example.erd.domain.store.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public ReviewResDTO.CreateReviewRes createReview(Long memberId, Long storeId, ReviewReqDTO.CreateReview dto)
    {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));


        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));


        Review review = ReviewConverter.toReview(member, store, dto);

        reviewRepository.save(review);

        return ReviewConverter.toCreateReviewRes(review);


    }
}
