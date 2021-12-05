package com.example.optikafiness.repository;

import com.example.optikafiness.model.entity.UserRoleEntity;
import com.example.optikafiness.model.entity.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity,Long> {
    UserRoleEntity findByRole(UserRole role);
}
