package com.example.optikafiness.web;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.optikafiness.model.entity.UserEntity;
import com.example.optikafiness.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;


import javax.validation.constraints.Size;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class UserRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository repository;
    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void testOpenRegisterForm() throws Exception {
        mockMvc.perform(
                get("/users/register")
        ).andExpect(status().isOk())
                .andExpect(view().name("Register"));
    }
    private static final String TEST_USER_USERNAME="gosho";
    @Test
    void testRegisterUser() throws Exception {
        mockMvc.perform(post("/users/register")
                .param("firstName","Gosho")
                .param("lastName","Goshov")
                .param("password","12345")
                .param("confirmPassword","12345")
                .param("username",TEST_USER_USERNAME)
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().is3xxRedirection());


        Optional<UserEntity> user=repository.findByUsername(TEST_USER_USERNAME);

        Assertions.assertTrue(user.isPresent());



    }
}