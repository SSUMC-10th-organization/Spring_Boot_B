package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long> {

  // id 내림차순
  @Query("SELECT r FROM Review r " +
      "JOIN FETCH r.store " +
      "WHERE r.user.id = :userId " +
      "AND (:cursorId IS NULL OR r.id < :cursorId) " +
      "ORDER BY r.id DESC")
  List<Review> findMyReviewsOrderById(
      @Param("userId") Long userId,
      @Param("cursorId") Long cursorId,
      Pageable pageable
  );

  // 별점 내림차순, 동점이면 id 내림차순
  // tiebreaker 조건이 빠지면 별점 동률 구간에서 페이지 누락/중복 발생함
  @Query("SELECT r FROM Review r " +
      "JOIN FETCH r.store " +
      "WHERE r.user.id = :userId " +
      "AND (:cursorRating IS NULL " +
      "     OR r.rating < :cursorRating " +
      "     OR (r.rating = :cursorRating AND r.id < :cursorId)) " +
      "ORDER BY r.rating DESC, r.id DESC")
  List<Review> findMyReviewsOrderByRating(
      @Param("userId") Long userId,
      @Param("cursorRating") Integer cursorRating,
      @Param("cursorId") Long cursorId,
      Pageable pageable
  );
}
