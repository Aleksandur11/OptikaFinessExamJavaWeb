package com.example.optikafiness.service;


import com.example.optikafiness.model.entity.service.UserRegistrationServiceModel;
import com.example.optikafiness.model.entity.service.UserRoleUpdateServiceModel;
import com.example.optikafiness.model.entity.view.AllUsersView;
import com.example.optikafiness.model.entity.view.UserView;

import java.util.List;

public interface UserService {
    void initializeUsersAndRoles();

    void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel);

    boolean isUserNameFree(String username);

    boolean isOwner(String username);

    void updateRole(UserRoleUpdateServiceModel serviceModel);

    List<AllUsersView> getUsers();

    List<AllUsersView>getAllUsersExceptCurrentUser(String name);

    UserView findById(Long id);

    void deleteUser(Long id);
}
