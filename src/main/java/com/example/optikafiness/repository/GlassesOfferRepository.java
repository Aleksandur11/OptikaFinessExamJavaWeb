package com.example.optikafiness.repository;

import com.example.optikafiness.model.entity.GlassesOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GlassesOfferRepository extends JpaRepository<GlassesOfferEntity,Long> {
    @Modifying
    @Query("DELETE FROM GlassesOfferEntity g where  g.id=:id")
    void deleteById(Long id);
}
