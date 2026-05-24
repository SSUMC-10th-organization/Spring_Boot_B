package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.user.entity.mapping.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
}