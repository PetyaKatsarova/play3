package com.example.play3.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {
    @CrossOrigin(origins = "*")
    @GetMapping("/login")
    public String loginPage() {
        return "redirect:/loginPage.html";
    }
}
