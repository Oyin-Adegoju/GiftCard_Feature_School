package com.example.gamewebshop.dto;

public class LoginResponse {

    private Long id;
    public String email;
    public String token;

    public LoginResponse(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
