package com.onlineshop.onlineshop.model;

import com.onlineshop.onlineshop.utils.Role;

import java.util.Date;
import java.util.Objects;

public class SignupResponse {

    private String username;

    private Role role;

    public SignupResponse(){}

    public SignupResponse(String username, Role role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignupResponse that = (SignupResponse) o;
        return Objects.equals(username, that.username) &&
                role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role);
    }

    @Override
    public String toString() {
        return "SignupResponse{" +
                "username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
