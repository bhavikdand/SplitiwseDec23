package com.example.splitwisedec232.services;

import com.example.splitwisedec232.exceptions.UserAlreadyExistsException;
import com.example.splitwisedec232.models.User;
import com.example.splitwisedec232.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        System.out.println("Creating US");
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String userName, String phoneNumber, String password) throws UserAlreadyExistsException {

        Optional<User> optionalUser = this.userRepository.findByUserName(userName);
        if(optionalUser.isPresent()){
            throw new UserAlreadyExistsException("User is already registered!");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setUserName(userName);
        user.setPassword(encodedPassword);
        user.setPhoneNumber(phoneNumber);

        return this.userRepository.save(user);
    }
}
