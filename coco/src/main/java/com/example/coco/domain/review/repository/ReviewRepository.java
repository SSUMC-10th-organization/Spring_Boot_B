package com.example.coco.domain.review.repository;

import com.example.coco.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 리뷰 작성을 위한 대상을 쿼리로 명시할 필요는 없으나, 저장 쿼리는 save()로 공통 처리됩니다.
}