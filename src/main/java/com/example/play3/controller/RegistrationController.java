package com.example.play3.controller;

import com.example.play3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private LoginService    loginService;
    @Autowired
    private UserService     userService;

    @GetMapping("/test")
    public ResponseEntity<String> testSys() {
        return ResponseEntity.ok("[play3 project, /test says]: Hello world :)");
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam String username, @RequestParam String password, Model model) {

        if (userService.usernameExists(username)) {
            model.addAttribute("message", "Username already exists. Please choose another username.");
           return ResponseEntity.ok("Username already exists. Please choose another username.");
        }
        userService.createUser(username, password);
        model.addAttribute("message", "Registration successful. You can now log in.");
        return ResponseEntity.ok("Registration successful. You can now log in.");
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/login")
    public String loginPage() {
        return "redirect:/loginPage.html";
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/verifyLogin")
    public ResponseEntity<Map<String, Object>> verifyLogin(@RequestParam String username, @RequestParam String password) {
        Map<String, Object> response = loginService.authenticate(username, password);

        return ResponseEntity.ok(response);
    }
}
