package com.example.play3.service;

import com.example.play3.domain.User;
import com.example.play3.repository.UserRepository;
import com.example.play3.utils.security.token.JWTToken;
import org.springframework.stereotype.Service;

@Service
public class AutharizationService {
    private final UserRepository    userRepository;
    private final JWTToken          jwtToken;

    public AutharizationService(UserRepository userRepository, JWTToken jwtToken) {
        this.userRepository = userRepository;
        this.jwtToken = jwtToken;
    }

    public User validateJWTToken(String jwtTokenGiven) {
        User user = userRepository.findUserByJwtToken(jwtTokenGiven.substring((7)));
        String verifiedToken = this.jwtToken.verifyJWTToken(jwtTokenGiven, user);
        if (verifiedToken.equals("Valid token")) {
            return user;
        }
        return null;
    }
}
