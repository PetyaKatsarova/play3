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
    public ResponseEntity<String> registerUser(@RequestParam String username, @RequestParam String password,
                                               @RequestParam String email, Model model) {
        // checks if username or email already exists are done in the saveUser method
        String message = userService.saveUser(username, password, email);
        model.addAttribute("message", message);
        if (message.equals("User was saved succesfully."))
            return ResponseEntity.ok("Registration successful. You can now log in.");
        return ResponseEntity.ok(message);
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
