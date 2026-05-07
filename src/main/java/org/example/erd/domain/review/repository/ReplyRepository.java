package org.example.erd.domain.review.repository;

import org.example.erd.domain.review.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
}
