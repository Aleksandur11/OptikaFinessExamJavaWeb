package com.example.optikafiness.service.impl;

import com.example.optikafiness.model.entity.UserEntity;
import com.example.optikafiness.model.entity.UserRoleEntity;
import com.example.optikafiness.model.entity.enums.UserRole;
import com.example.optikafiness.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class OptikaFinessUserImplTest {

    private UserEntity testUser;

    private OptikaFinessUserImpl serviceToTest;

    private UserRoleEntity adminRole,userRole;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void init(){
    serviceToTest=new OptikaFinessUserImpl(mockUserRepository);
    adminRole=new UserRoleEntity();
    adminRole.setRole(UserRole.ADMIN);
    userRole= new UserRoleEntity();
    userRole.setRole(UserRole.USER);


    testUser=new UserEntity();
    testUser.setUsername("admin");
    testUser.setRoles(Set.of(adminRole));
    testUser.setPassword("admin");
    }
@Test
    void testUserNotFound(){
        Assertions.assertThrows(UsernameNotFoundException.class,()->
            serviceToTest.loadUserByUsername("invalid_username")
        );

    }
    @Test
    void testUserFound(){
        Mockito.when(mockUserRepository.findByUsername(testUser.getUsername())).thenReturn(Optional.of(testUser));

       var actual= serviceToTest.loadUserByUsername(testUser.getUsername());

       Assertions.assertEquals(actual.getUsername(),testUser.getUsername());

       String actualRole=actual.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
       String expectedRole="ROLE_ADMIN";
       Assertions.assertEquals(expectedRole,actualRole);
    }
}
