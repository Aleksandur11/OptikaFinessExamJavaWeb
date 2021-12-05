package com.example.optikafiness.repository;

import com.example.optikafiness.model.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity,Long> {
    Optional<BrandEntity> findByName(String name);
}
