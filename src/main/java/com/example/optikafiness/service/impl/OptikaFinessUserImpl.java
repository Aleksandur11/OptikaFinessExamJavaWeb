package com.example.optikafiness.service.impl;


import com.example.optikafiness.model.entity.UserEntity;
import com.example.optikafiness.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OptikaFinessUserImpl implements UserDetailsService {

    private UserRepository userRepository;


    public OptikaFinessUserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity userEntity=userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Username "+username+" not found!"));

    return mapToUserDetails(userEntity);
    }


    private static UserDetails mapToUserDetails(UserEntity user){


        List<GrantedAuthority>authorities=user.getRoles()
                .stream()
                .map(r-> new SimpleGrantedAuthority("ROLE_"+ r.getRole().name()))
                .collect(Collectors.toList());

        return new User(user.getUsername(), user.getPassword(), authorities);

    }


}
