package com.example.splitwisedec232.dtos;

import lombok.Data;

@Data
public class RegisterUserRequestDto {
    private String userName;
    private String phoneNumber;
    private String password;
}
