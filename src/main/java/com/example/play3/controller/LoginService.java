package com.example.play3.controller;

import com.example.play3.domain.User;
import com.example.play3.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Map<String, Object> authenticate(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        Map<String, Object> response = new HashMap<>();

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                response.put("success", true);
            } else {
                response.put("success", false);
                response.put("message", "Incorrect password.");
            }
        } else {
            response.put("success", false);
            response.put("message", "No user found with this username.");
        }
        System.out.println("********** "+response.toString()+"****************");
        return response;
    }
}
