package org.example.erd.domain.member.repository;

import org.example.erd.domain.member.entity.mapping.MemberFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFoodRepository extends JpaRepository<MemberFood,Long> {
}
