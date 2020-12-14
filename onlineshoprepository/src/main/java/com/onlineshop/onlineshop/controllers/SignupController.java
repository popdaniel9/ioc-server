package com.onlineshop.onlineshop.controllers;

import com.onlineshop.onlineshop.entity.UserEntity;
import com.onlineshop.onlineshop.model.FeedbackResponse;
import com.onlineshop.onlineshop.model.SignupRequest;
import com.onlineshop.onlineshop.model.SignupResponse;
import com.onlineshop.onlineshop.services.CustomUserDetailsService;
import com.onlineshop.onlineshop.utils.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/signup")
public class SignupController {
    private final CustomUserDetailsService customUserDetailsService;

    public SignupController(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> insertUser(@RequestBody SignupRequest userData){
        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(userData.getUsername());
            userEntity.setPassword(userData.getPassword());
            userEntity.setEmail(userData.getEmail());
            userEntity.setNumarTelefon(userData.getNumarTelefon());
            String role = userData.getRole();
            if(role.equals("ADMIN") || role.equals("admin")){
                Role r = Role.ADMIN;
                userEntity.setRole(r);
            }else {
                Role r = Role.USER;
                userEntity.setRole(r);
            }
            UserEntity saved = customUserDetailsService.saveUser(userEntity);
            if(saved != null) {
                SignupResponse signupResponse = new SignupResponse();
                signupResponse.setUsername(userEntity.getUsername());
                signupResponse.setRole(userEntity.getRole());
                return new ResponseEntity<SignupResponse>(signupResponse, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<String>("User exists", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return new ResponseEntity<String>("Could not register user", HttpStatus.BAD_REQUEST);
        }
    }
}
