package com.practice.userAuth.Payload.Response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    private String message;
    private String status;
    private String firstName;
    private String lastName;

    public JwtResponse(String token, Long id, String username, String email, List<String> roles, String message, String status, String firstName, String lastName) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.message = message;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
