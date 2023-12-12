package com.example.splitwisedec232.controller;

import com.example.splitwisedec232.dtos.RegisterUserRequestDto;
import com.example.splitwisedec232.dtos.RegisterUserResponseDto;
import com.example.splitwisedec232.dtos.ResponseStatus;
import com.example.splitwisedec232.models.User;
import com.example.splitwisedec232.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        System.out.println("Creating UC");
        this.userService = userService;
    }

    public RegisterUserResponseDto registerUser(RegisterUserRequestDto requestDto){
        RegisterUserResponseDto responseDto = new RegisterUserResponseDto();
        try{
            User user = userService.registerUser(requestDto.getUserName(), requestDto.getPhoneNumber(), requestDto.getPassword());
            responseDto.setId(user.getId());
            responseDto.setUserName(user.getUserName());
            responseDto.setPhoneNumber(user.getPhoneNumber());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e){
            responseDto.setErrorMessage(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
