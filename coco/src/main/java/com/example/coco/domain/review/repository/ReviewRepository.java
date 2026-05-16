package com.example.coco.domain.review.repository;

import com.example.coco.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 1. ID 최신순 커서 기반 쿼리
    @Query("SELECT r FROM Review r JOIN FETCH r.member m " +
            "WHERE r.member.id = :memberId AND (:cursor IS NULL OR r.id < :cursor) " +
            "ORDER BY r.id DESC")
    List<Review> findMyReviewsCursorById(@Param("memberId") Long memberId,
                                         @Param("cursor") Long cursor,
                                         Pageable pageable);

    // 2. 별점 높은순 커서 기반 쿼리 (별점이 같으면 ID가 작은 순 정렬)
    @Query("SELECT r FROM Review r JOIN FETCH r.member m " +
            "WHERE r.member.id = :memberId " +
            "AND (:cursorId IS NULL OR r.rating < :cursorRating OR (r.rating = :cursorRating AND r.id < :cursorId)) " +
            "ORDER BY r.rating DESC, r.id DESC")
    List<Review> findMyReviewsCursorByRating(@Param("memberId") Long memberId,
                                             @Param("cursorRating") Float cursorRating,
                                             @Param("cursorId") Long cursorId,
                                             Pageable pageable);
}