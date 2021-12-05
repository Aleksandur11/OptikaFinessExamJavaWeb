package com.example.optikafiness.service.impl;

import com.example.optikafiness.model.entity.UserEntity;
import com.example.optikafiness.model.entity.UserRoleEntity;
import com.example.optikafiness.model.entity.enums.UserRole;
import com.example.optikafiness.model.entity.view.AllUsersView;
import com.example.optikafiness.model.entity.view.UserView;
import com.example.optikafiness.repository.UserRepository;
import com.example.optikafiness.repository.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    private UserServiceImpl serviceToTest;

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private UserRoleRepository mockUserRoleRepository;
    @Mock
    private  OptikaFinessUserImpl mockOptikaFinessUserImpl;
    @Mock
    private  ModelMapper mockModelMapper;
    @Mock
    private PasswordEncoder mockPasswordEncoder;
    private UserEntity user;
    private UserEntity user2,user3,userUser,eleonora,pesho;
    private UserRoleEntity adminRole,userRole;
    private List<UserEntity> allUsers;

    @BeforeEach
    void init(){
        serviceToTest=new UserServiceImpl(mockPasswordEncoder,mockUserRepository,mockUserRoleRepository,mockOptikaFinessUserImpl,mockModelMapper);
        user=new UserEntity();
        user2=new UserEntity();
        user3=new UserEntity();
        userUser=new UserEntity();


        adminRole=new UserRoleEntity();
        adminRole.setRole(UserRole.ADMIN);
        userRole=new UserRoleEntity();
        userRole.setRole(UserRole.USER);

        userUser.setUsername("merco");
        userUser.setRoles(Set.of(userRole));
        user.setPassword("admin");



        user.setUsername("admin");
        user.setRoles(Set.of(adminRole));
       user.setPassword("admin");
       user.setId(1L);

       user2.setFirstName("Pesho");
       user2.setLastName("Peshov");
       user2.setRoles(Set.of(adminRole));

        user3.setFirstName("Gesho");
        user3.setLastName("Geshov");
        user3.setRoles(Set.of(adminRole));



       allUsers=new ArrayList<>();
       allUsers.add(user3);
       allUsers.add(user2);




    }
    @Test
    void testIsUserNameFree(){
        var expected2=serviceToTest.isUserNameFree("Borko");
        var actual2=true;
        Assertions.assertEquals(expected2,actual2);

    }
    @Test
    void testIsUsernameTaken(){
        Mockito.when(mockUserRepository.findByUsernameIgnoreCase(user.getUsername())).thenReturn(Optional.of(user));
        var actual=serviceToTest.isUserNameFree(user.getUsername());
        var expected=false;
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void testGetAllUsers(){
        AllUsersView userView2=new AllUsersView();
        AllUsersView userView3=new AllUsersView();

        Mockito.when(mockUserRepository.findAll()).thenReturn(allUsers.stream().toList());
        Mockito.when(mockModelMapper.map(user2,AllUsersView.class)).thenReturn(userView2);
        Mockito.when(mockModelMapper.map(user3,AllUsersView.class)).thenReturn(userView3);



        var actual= new ArrayList<>(serviceToTest.getUsers()).stream().map(AllUsersView::getFirstName).collect(Collectors.joining(""));
        var expected=allUsers.stream().map((UserEntity::getFirstName)).collect(Collectors.joining());
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void findByIdTest(){

        UserView userView=new UserView();
        userView.setRole(UserRole.ADMIN);
        userView.setId(1L);
        Mockito.when(mockUserRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(mockModelMapper.map(user,UserView.class)).thenReturn(userView);

        var actual=serviceToTest.findById(1L);


        Assertions.assertEquals(userView,actual);
    }
    @Test
    void isOwnerTestReturnsFalseWhenEmpty(){
        Mockito.when(mockUserRepository.findByUsername("admin")).thenReturn(Optional.empty());

        Assertions.assertFalse(serviceToTest.isOwner("admin"));

    }
    @Test
    void isOwnerReturnsTrueWhenUserIsAdmin(){
        Mockito.when(mockUserRepository.findByUsername("admin")).thenReturn(Optional.of(user));

        Assertions.assertTrue(serviceToTest.isOwner("admin"));

    }
    @Test
    void isOwnerReturnsFalseWhenUserIsUser(){
        Mockito.when(mockUserRepository.findByUsername("merco")).thenReturn(Optional.of(userUser));

        Assertions.assertFalse(serviceToTest.isOwner("merco"));
    }
    @Test
    void testInitializeUsersAndRoles(){
       Mockito.when(mockPasswordEncoder.encode("admin")).thenReturn("nimda");
       Mockito.when(mockPasswordEncoder.encode("test")).thenReturn("tset");
    Mockito.when(mockUserRoleRepository.findByRole(UserRole.ADMIN)).thenReturn((adminRole));
    Mockito.when(mockUserRoleRepository.findByRole(UserRole.USER)).thenReturn(userRole);



      serviceToTest.initializeUsersAndRoles();
        Mockito.verify(mockUserRoleRepository,Mockito.times(1)).saveAll(Mockito.any());

        Mockito.verify(mockUserRepository,Mockito.times(2)).save(Mockito.any());

    }

}