package com.practice.userAuth.Payload.Request;

import lombok.Data;

import java.util.Set;

@Data
public class SignUpRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private String pinCode;
    private String mobNo;
    private String adharNo;
    private String username;
    private String email;
    private Set<String> role;
    private String password;
    private String panNo;

}
