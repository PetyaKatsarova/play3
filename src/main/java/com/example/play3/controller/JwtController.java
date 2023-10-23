package com.example.play3.controller;

import com.example.play3.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JwtController {

    private final JwtService jwtService;

    @Autowired
    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/get-jwt-token")
    @ResponseBody
    public ResponseEntity<String> getJwtToken() {
        try {
            String jwtToken = jwtService.getJwtToken();
            return ResponseEntity.ok("Bearer " + jwtToken);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error in JwtController: " + e.getMessage());
        }
    }
}
