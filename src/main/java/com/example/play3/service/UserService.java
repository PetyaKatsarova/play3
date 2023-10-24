package com.example.play3.service;

import com.example.play3.domain.User;
import com.example.play3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository    userRepository;
    //    private final PasswordEncoder passwordEncoder;
    private final JwtService        jwtService; // Inject JwtService

    @Autowired
    public UserService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public String saveUser(String username, String password, String email) {
        if (findByUsername(username).isPresent())
            return "User with this username already exists. Try another one";
        if (findByEmail(email).isPresent())
            return "User with this email already exists. Try another one";

        String jwtToken = jwtService.getJwtToken();
        String jwtTokenToSave = jwtToken.length() > 500 ? jwtToken.substring(0, 500) : jwtToken;
        User newUser = new User(username, password, jwtTokenToSave, email);
        userRepository.save(newUser);
        return "User was saved succesfully.";
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) { return userRepository.findByEmail(email); }
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
