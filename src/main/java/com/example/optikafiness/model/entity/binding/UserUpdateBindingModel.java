package com.example.optikafiness.model.entity.binding;

import com.example.optikafiness.model.entity.enums.UserRole;

public class UserUpdateBindingModel {
    private Long id;
   private Long userId;
    private UserRole role;

    public UserUpdateBindingModel() {
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
