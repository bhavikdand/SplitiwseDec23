package com.example.splitwisedec232.commands;

import com.example.splitwisedec232.controller.UserController;
import com.example.splitwisedec232.dtos.RegisterUserRequestDto;
import com.example.splitwisedec232.dtos.RegisterUserResponseDto;
import com.example.splitwisedec232.dtos.ResponseStatus;
import com.example.splitwisedec232.exceptions.InvalidCommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserCommand implements Command{

    public static final String REGISTER_USER_COMMAND = "Register";

    private UserController controller;

    @Autowired
    public RegisterUserCommand(UserController controller){
        System.out.println("Creating RUC");
        CommandRegistry.getInstance().register(REGISTER_USER_COMMAND, this);
        this.controller = controller;
    }


    @Override
    public void execute(String input) throws InvalidCommandException {
        if(input == null || input.length() == 0){
            throw  new InvalidCommandException("Register user command cannot be null or empty");
        }
        String splits[] = input.split(" ");
        if(splits.length != 4){
            throw new InvalidCommandException("Register user command is not in expected format");
        }
        if(!splits[0].equals(REGISTER_USER_COMMAND)){
            throw new InvalidCommandException("Register user command is not in expected format");
        }
        RegisterUserRequestDto requestDto = new RegisterUserRequestDto();
        requestDto.setUserName(splits[1]);
        requestDto.setPhoneNumber(splits[2]);
        requestDto.setPassword(splits[3]);

        // Call the registerUser method of UserController
        RegisterUserResponseDto responseDto = controller.registerUser(requestDto);
        if(responseDto.getResponseStatus().equals(ResponseStatus.FAILURE)){
            System.out.println("Error:" + responseDto.getErrorMessage());
        } else {
            System.out.println("User id:" + responseDto.getId());
        }
    }
}
