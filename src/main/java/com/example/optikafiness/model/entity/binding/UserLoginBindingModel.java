package com.example.optikafiness.model.entity.binding;

import javax.validation.constraints.Size;

public class UserLoginBindingModel {

    private String userName;
    private String password;

    public UserLoginBindingModel() {
    }
    @Size(min = 5)
    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    @Size(min = 5)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
