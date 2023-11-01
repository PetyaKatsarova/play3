package com.example.play3.communication.controller;

import com.example.play3.domain.User;
import com.example.play3.repository.UserRepository;
import com.example.play3.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "*")
    @PostMapping("/login/user")
    public ResponseEntity<Map<String, Object>> loginTenant(@RequestBody Map<String, String> loginDetails) {
        String email = loginDetails.get("loginEmail");
        String password = loginDetails.get("loginPassword");

        Map<String, Object> response = new HashMap<>();
        try {
            User user = loginService.authenticateUser(email, password);
            if (user != null) {
                String jwt = loginService.generateJWTToken(email);
                user.setJwtToken(jwt);
                userRepository.save(user);
                response.put("success", true);
                response.put("jwtToken", jwt);
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Incorrect username or password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

//    @CrossOrigin(origins = "*")
//    @PostMapping("/login/landlord")
//    public ResponseEntity<Map<String, Object>> loginLandlord(@RequestBody Map<String, String> loginDetails) {
//        String email = loginDetails.get("loginEmail");
//        String password = loginDetails.get("loginPassword");
//
//        Map<String, Object> response = new HashMap<>();
//        try {
//            User user = loginService.authenticateUser(email, password);
//            if (user != null) {
//                String jwt = loginService.generateJWTToken(email);
//                user.setJwtToken(jwt);
//                userRepository.save(user);
//                response.put("success", true);
//                response.put("jwtToken", jwt);
//                return ResponseEntity.ok(response);
//            } else {
//                response.put("success", false);
//                response.put("message", "Incorrect username or password");
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//            }
//        } catch (Exception e) {
//            response.put("success", false);
//            response.put("message", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }
}
