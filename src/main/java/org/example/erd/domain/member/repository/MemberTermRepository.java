package org.example.erd.domain.member.repository;

import org.example.erd.domain.member.entity.mapping.MemberTerm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTermRepository extends JpaRepository<MemberTerm,Long> {
}
