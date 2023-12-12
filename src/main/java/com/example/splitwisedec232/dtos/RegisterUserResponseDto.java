package com.example.splitwisedec232.dtos;

import lombok.Data;

@Data
public class RegisterUserResponseDto {

    private String userName;
    private String phoneNumber;
    private long id;
    private ResponseStatus responseStatus;
    private String errorMessage;
}
