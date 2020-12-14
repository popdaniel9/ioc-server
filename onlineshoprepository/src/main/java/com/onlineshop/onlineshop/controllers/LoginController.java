package com.onlineshop.onlineshop.controllers;

import com.onlineshop.onlineshop.jwt.JwtUtil;
import com.onlineshop.onlineshop.model.LoginRequest;
import com.onlineshop.onlineshop.model.LoginResponse;
import com.onlineshop.onlineshop.services.CustomUserDetailsService;
import com.onlineshop.onlineshop.utils.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/user/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

    public LoginController(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            System.out.println(authentication);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());

        String jwt = jwtUtil.generateToken(userDetails);

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        Role role = Role.valueOf(authorities.iterator().next().toString());
        LoginResponse response = new LoginResponse();
        response.setJwt(jwt);
        response.setRole(role);
        response.setUsername(userDetails.getUsername());
        response.setExpirationDate(jwtUtil.EXPIRATION_TIME);
        return ResponseEntity.ok(response);
    }
}