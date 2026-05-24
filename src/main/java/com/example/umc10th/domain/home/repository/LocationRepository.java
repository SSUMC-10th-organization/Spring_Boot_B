package com.example.umc10th.domain.home.repository;

import com.example.umc10th.domain.home.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}