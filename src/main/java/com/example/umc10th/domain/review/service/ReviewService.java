package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.review.dto.res.ReviewResDTO;
import com.example.umc10th.domain.review.enums.ReviewSortType;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.req.ReviewCreateReqDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

  private final ReviewRepository reviewRepository;
  private final StoreRepository storeRepository;
  private final UserRepository userRepository;

  @Transactional
  public Long createReview(Long storeId, ReviewCreateReqDTO dto) {

    // 가게 조회, 없으면 예외
    Store store = storeRepository.findById(storeId)
        .orElseThrow(() -> new ReviewException(MissionErrorCode.STORE_NOT_FOUND));

    // 유저 조회
    User user = userRepository.findById(dto.getUserId())
        .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

    Review review = ReviewConverter.toReview(dto, store, user);
    Review saved = reviewRepository.save(review);

    return saved.getId();
  }

  @Transactional
  public ReviewResDTO.CursorPage<ReviewResDTO.MyReviewItem> getMyReviews(
      Long userId,
      ReviewSortType sort,
      Long cursorId,
      Integer cursorRating,
      Integer size
  ) {
    // 유저 존재 검증
    if (!userRepository.existsById(userId)) {
      throw new UserException(UserErrorCode.USER_NOT_FOUND);
    }

    // hasNext 판정을 위해 size + 1 개 가져오기
    PageRequest limit = PageRequest.of(0, size + 1);

    // 정렬 모드에 따라 다른 쿼리 호출
    List<Review> rows = switch (sort) {
      case ID -> reviewRepository.findMyReviewsOrderById(userId, cursorId, limit);
      case RATING -> reviewRepository.findMyReviewsOrderByRating(
          userId, cursorRating, cursorId, limit
      );
    };

    // hasNext 판정 + 마지막 1개 잘라내기
    boolean hasNext = rows.size() > size;
    List<Review> page = hasNext ? rows.subList(0, size) : rows;

    // 다음 커서 추출 — 마지막 행의 정렬 키 값
    Long nextCursorId = null;
    Integer nextCursorRating = null;
    if (hasNext && !page.isEmpty()) {
      Review last = page.get(page.size() - 1);
      nextCursorId = last.getId();
      // RATING 모드일 때만 nextCursorRating 도 채움
      if (sort == ReviewSortType.RATING) {
        nextCursorRating = last.getRating();
      }
    }

    return ReviewConverter.toCursorPage(
        page.stream().map(ReviewConverter::toMyReviewItem).toList(),
        nextCursorId,
        nextCursorRating,
        hasNext
    );
  }
}
