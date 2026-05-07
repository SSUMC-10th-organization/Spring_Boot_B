package org.example.erd.domain.review.repository;

import org.example.erd.domain.review.entity.ReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewPhotoRepository extends JpaRepository<ReviewPhoto,Long> {
}
