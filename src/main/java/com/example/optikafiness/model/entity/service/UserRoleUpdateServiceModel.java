package com.example.optikafiness.model.entity.service;

import com.example.optikafiness.model.entity.enums.UserRole;

public class UserRoleUpdateServiceModel {
    private Long id;
    private Long userId;
    private UserRole role;

    public UserRoleUpdateServiceModel() {
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
