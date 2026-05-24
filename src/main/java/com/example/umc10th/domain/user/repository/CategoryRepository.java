package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.user.entity.Category;
import com.example.umc10th.domain.user.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByCategoryNameIn(List<CategoryName> names);
    // 회원가입 시 선택한 음식 종류 이름으로 Category 엔티티 한번에 조회
}