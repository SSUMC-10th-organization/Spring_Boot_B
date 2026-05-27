package org.example.erd.domain.member.repository;


import org.example.erd.domain.member.entity.Member;
import org.example.erd.domain.member.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findBySocialTypeAndSocialUid(SocialType socialType, String socialUid);
}
