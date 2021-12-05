package com.example.optikafiness.web;

import com.example.optikafiness.model.entity.view.AllUsersView;
import com.example.optikafiness.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class UserRolesController {
    private final UserService userService;

    public UserRolesController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/api/all/users")
    public ResponseEntity<List<AllUsersView>> getAllUsersTwo(Principal principal){


        return ResponseEntity.ok(userService.getUsers());


    }
}
