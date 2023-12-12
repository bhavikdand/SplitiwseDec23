package com.example.splitwisedec232.services;

import com.example.splitwisedec232.exceptions.UserAlreadyExistsException;
import com.example.splitwisedec232.models.User;

public interface UserService {

    public User registerUser(String userName, String phoneNumber, String password) throws UserAlreadyExistsException;
}
