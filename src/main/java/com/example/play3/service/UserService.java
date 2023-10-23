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

    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public void saveUser(String username, String password) {
        if (usernameExists(username)) {
            return ;
        }
        String jwtToken = jwtService.getJwtToken();
        String jwtTokenToSave = jwtToken.length() > 500 ? jwtToken.substring(0, 500) : jwtToken;
        User newUser = new User(username, password, jwtTokenToSave);
        userRepository.save(newUser);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
