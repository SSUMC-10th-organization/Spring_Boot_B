package org.example.erd.domain.review.repository;

import org.example.erd.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    // ID순 - 커서 없을 때
    Slice<Review>
    findByMember_IdOrderByIdDesc(Long memberId, Pageable pageable);

    // ID순 - 커서 있을 때
    Slice<Review>
    findByMember_IdAndIdLessThanOrderByIdDesc
    (Long memberId, Long id, Pageable pageable
    );

    // 별점순 - 커서 없을 때
    Slice<Review>
    findByMember_IdOrderByScoreDescIdDesc(
            Long memberId, Pageable pageable
    );

    // 별점순 - 커서 있을 때(동점 처리 포함)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND (r.score < :score OR (r.score =:score AND r.id <:id)) ORDER BY r.score DESC, r.id DESC")
    Slice<Review>
    findByMemberIdWithScoreCursor(
            @Param("memberId") Long memberId,
            @Param("score") Float score,
            @Param("id") Long id,
            Pageable pageable
    );


}
