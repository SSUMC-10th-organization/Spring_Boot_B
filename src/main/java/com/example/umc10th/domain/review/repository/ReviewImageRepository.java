package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {

    List<ReviewImage> findAllByReviewId(Long reviewId);
    // 리뷰 조회할 때 해당 리뷰의 이미지 목록 가져올 때
}
