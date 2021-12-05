package com.example.optikafiness.service.impl;


import com.example.optikafiness.model.entity.UserEntity;
import com.example.optikafiness.model.entity.UserRoleEntity;
import com.example.optikafiness.model.entity.enums.UserRole;

import com.example.optikafiness.model.entity.service.UserRegistrationServiceModel;
import com.example.optikafiness.model.entity.service.UserRoleUpdateServiceModel;
import com.example.optikafiness.model.entity.view.AllUsersView;

import com.example.optikafiness.model.entity.view.UserView;
import com.example.optikafiness.repository.UserRepository;
import com.example.optikafiness.repository.UserRoleRepository;
import com.example.optikafiness.service.UserService;
import com.example.optikafiness.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final OptikaFinessUserImpl optikaFinessUserImpl;
    private final ModelMapper modelMapper;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository, OptikaFinessUserImpl optikaFinessUserImpl, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.optikaFinessUserImpl = optikaFinessUserImpl;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initializeUsersAndRoles() {
        initializeRoles();
        initializeUsers();
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {

            UserRoleEntity adminRole = userRoleRepository.findByRole(UserRole.ADMIN);
            UserRoleEntity userRole = userRoleRepository.findByRole(UserRole.USER);

            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
                 admin.setPassword(passwordEncoder.encode("admin"));
                   admin.setFirstName("Eleonora");
                    admin.setLastName("Drashkova");
                    admin.setActive(true);

            admin.setRoles(Set.of(adminRole));
            userRepository.save(admin);
            //TODO:REMOVE WHEN TEST IS DONE
            UserEntity pesho = new UserEntity();
            pesho.setUsername("pesho");
                 pesho.setPassword(passwordEncoder.encode("test"));
                    pesho.setFirstName("Pesho");
                    pesho.setLastName("Petrov");
                    pesho.setActive(true);

            pesho.setRoles(Set.of(userRole));
            userRepository.save(pesho);
        }
    }
    public void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel) {
        UserRoleEntity userRoleEntity=userRoleRepository.findByRole(UserRole.USER);
        UserEntity newUser=new UserEntity();
        newUser.setUsername(userRegistrationServiceModel.getUsername());
        newUser.setFirstName(userRegistrationServiceModel.getFirstName());
        newUser.setLastName(userRegistrationServiceModel.getLastName());
        newUser.setActive(true);
        newUser.setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword()));
        newUser.setRoles((Set.of(userRoleEntity)));
        newUser=userRepository.save(newUser);

        UserDetails principal=optikaFinessUserImpl.loadUserByUsername(newUser.getUsername());
        Authentication authentication=new UsernamePasswordAuthenticationToken(
                principal,newUser.getPassword(),
                principal.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);


    }

    @Override
    public boolean isUserNameFree(String username) {
        return userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

    @Override
    public boolean isOwner(String username) {
        Optional<UserEntity> user=userRepository.findByUsername(username);
        if(user.isEmpty()){
            return false;
        }else {
            UserEntity user1=user.get();
            return isAdmin(user1);
        }
    }

    @Override
    public void updateRole(UserRoleUpdateServiceModel serviceModel) {
//TODO:SET ROLES
        UserRoleEntity adminRole = userRoleRepository.findByRole(UserRole.ADMIN);
           UserEntity userEntity=userRepository.findById(serviceModel.getId())
                    .orElseThrow(()->new ObjectNotFoundException("User with id:"+serviceModel.getUserId()+"not found!"));
           userEntity.setRoles(Set.of(adminRole));
      userRepository.save(userEntity);
    }

    @Override
    public List<AllUsersView> getUsers() {
        var users= userRepository.findAll();


        if(users.isEmpty()){
            throw new ObjectNotFoundException("No users found");
        }
        return users.stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public List<AllUsersView> getAllUsersExceptCurrentUser(String name) {
        var users= userRepository.findAll().stream().filter(u->!u.getUsername().equals(name));
        return users.map(this::map).collect(Collectors.toList());
    }

    @Override
    public UserView findById(Long id) {

        return this.userRepository.findById(id).map(this::mapDetailedView).get();

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    private boolean isAdmin(UserEntity user){
        return user.getRoles().stream().map(UserRoleEntity::getRole).filter(r -> r==UserRole.ADMIN).findAny().isPresent();

    }

    private void initializeRoles() {

        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setRole(UserRole.ADMIN);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setRole(UserRole.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }
    private AllUsersView map(UserEntity userEntity) {

       String contactString = userEntity.getRoles().stream().map(UserRoleEntity::toString).collect(Collectors.joining(","));
        AllUsersView allUsersView = this.modelMapper.map(userEntity, AllUsersView.class);
        allUsersView.setFirstName(userEntity.getFirstName());
        allUsersView.setLastName(userEntity.getLastName());
        allUsersView.setUserRole(contactString);
        allUsersView.setUserId(userEntity.getId());

        return allUsersView;
    }
    private UserView mapDetailedView(UserEntity user) {
        UserView userView = this.modelMapper.map(user, UserView.class);

        userView.setRole(UserRole.ADMIN);
        return userView;
    }
}
