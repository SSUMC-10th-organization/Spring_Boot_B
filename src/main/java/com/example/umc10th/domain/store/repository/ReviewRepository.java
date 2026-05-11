package com.example.umc10th.domain.store.repository;

import com.example.umc10th.domain.store.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    // ID 순 조회 (최신순 등)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND r.id < :cursorId ORDER BY r.id DESC")
    List<Review> findByMemberIdAndIdLessThanOrderByIdDesc(@Param("memberId") Long memberId, @Param("cursorId") Long cursorId, Pageable pageable);

    // 첫 페이지 조회 (ID 순)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.id DESC")
    List<Review> findByMemberIdOrderByIdDesc(@Param("memberId") Long memberId, Pageable pageable);

    // 별점 순 조회 (높은 순, 동일 별점일 경우 ID 최신순)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND (r.star < :cursorStar OR (r.star = :cursorStar AND r.id < :cursorId)) ORDER BY r.star DESC, r.id DESC")
    List<Review> findByMemberIdAndStarLessThanOrderByStarDescIdDesc(@Param("memberId") Long memberId, @Param("cursorStar") Float cursorStar, @Param("cursorId") Long cursorId, Pageable pageable);

    // 첫 페이지 조회 (별점 순)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.star DESC, r.id DESC")
    List<Review> findByMemberIdOrderByStarDescIdDesc(@Param("memberId") Long memberId, Pageable pageable);
}
