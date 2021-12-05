package com.example.optikafiness.repository;

import com.example.optikafiness.model.entity.LensesBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LensesBrandRepository extends JpaRepository<LensesBrandEntity,Long> {

    Optional<LensesBrandEntity> findByName(String name);
}
