package org.example.erd.domain.member.repository;

import org.example.erd.domain.member.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term,Long> {

}
