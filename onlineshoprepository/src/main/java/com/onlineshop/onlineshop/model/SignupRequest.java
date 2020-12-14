package com.onlineshop.onlineshop.model;

import java.util.Objects;

public class SignupRequest {

    private String username;
    private String password;
    private String email;
    private String numarTelefon;
    private String role;

    public SignupRequest(Long id, String username, String password, String email, String numarTelefon, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.numarTelefon = numarTelefon;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignupRequest that = (SignupRequest) o;
        return
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email) &&
                Objects.equals(numarTelefon, that.numarTelefon) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, numarTelefon, role);
    }

    @Override
    public String toString() {
        return "SignupRequest{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", numarTelefon='" + numarTelefon + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
