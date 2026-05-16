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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public ReviewResDTO.Pagination<ReviewResDTO.ReviewItem> getMyReviews(
            Long memberId,
            Integer pageSize,
            String cursor,
            String query
    ) {
        PageRequest pageRequest = PageRequest.of(0,pageSize);
        Slice<Review> reviewSlice;
        String nextCursor;

        if(cursor.equals("-1")) {
            // 커서 없을 때
            if(query.equalsIgnoreCase("score")){
                reviewSlice = reviewRepository.findByMember_IdOrderByScoreDescIdDesc(memberId,pageRequest);
            }
            else{
                reviewSlice = reviewRepository.findByMember_IdOrderByIdDesc(memberId,pageRequest);
            }
        }
        else{
            // 커서 있을 때
            String[] parts = cursor.split(":");
            if(query.equalsIgnoreCase("score")){
                Float scoreCursor = Float.parseFloat(parts[1]);
                Long idCursor = Long.parseLong(parts[2]);
                reviewSlice = reviewRepository.findByMemberIdWithScoreCursor(memberId,scoreCursor,idCursor,pageRequest);
            }
            else{
                Long idCursor = Long.parseLong(parts[1]);
                reviewSlice = reviewRepository.findByMember_IdAndIdLessThanOrderByIdDesc(memberId,idCursor,pageRequest);
            }
        }

        if (reviewSlice.hasNext()){
            Review last = reviewSlice.getContent().get(reviewSlice.getContent().size() -1);
            if(query.equalsIgnoreCase("score")) {
                nextCursor = "score:" + last.getScore()+":"+ last.getId();
            } else{
                nextCursor = "id:" + last.getId();
            }
        }
        else{
            nextCursor = null;
        }

        List<ReviewResDTO.ReviewItem> items = reviewSlice.getContent().stream()
                .map(ReviewConverter::toReviewItem)
                .toList();

        return ReviewConverter.toReviewPagination(items,reviewSlice.hasNext(),nextCursor,pageSize);
    }
}
