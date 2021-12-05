package com.example.optikafiness.repository;

import com.example.optikafiness.model.entity.LensesOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LensesOfferRepository extends JpaRepository<LensesOfferEntity,Long> {
    @Modifying
    @Query("DELETE FROM LensesOfferEntity g where  g.id=:id")
    void deleteById(Long id);
}
