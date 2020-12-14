package com.onlineshop.onlineshop.services;


import com.onlineshop.onlineshop.entity.UserEntity;
import com.onlineshop.onlineshop.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.getUserEntityByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        return buildUserForAuthentication(user, Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
    }


    public UserEntity saveUser(UserEntity user){
        try {
            UserEntity userEntity = userRepository.getUserEntityByUsername(user.getUsername());
            if(userEntity == null){
                return userRepository.save(user);
            }
            return null;
        }catch (Exception ex){
            return userRepository.save(user);
        }
    }


    private UserDetails buildUserForAuthentication(UserEntity user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                true, true, true, true, authorities);
    }
}