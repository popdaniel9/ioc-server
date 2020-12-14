package com.onlineshop.onlineshop.entity;

import com.onlineshop.onlineshop.utils.Role;

import javax.persistence.*;
import java.util.*;

@Table (name = "utilizator")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String numarTelefon;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    public UserEntity(Long id, String username, String password, String email, String numarTelefon) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.numarTelefon = numarTelefon;
    }

    public UserEntity(String username, String password, String email, String numarTelefon, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.numarTelefon = numarTelefon;
        this.role = role;
    }

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
