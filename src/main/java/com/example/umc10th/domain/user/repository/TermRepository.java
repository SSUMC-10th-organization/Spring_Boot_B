package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.user.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermRepository extends JpaRepository<Term, Long> {

    List<Term> findAllByIdIn(List<Long> ids);
    // 회원가입할 때 유저가 동의한 약관 ID 목록으로 Term 엔티티 한번에 조회
}
