package com.example.play3.controller;

import com.example.play3.domain.User;
import com.example.play3.service.LoginService;
import com.example.play3.service.RegistrationService;
import com.example.play3.utils.event.RegistrationCompleteEvent;
import com.example.play3.utils.security.password.SaltMaker;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService             registrationService;

    private final ApplicationEventPublisher publisher;

    public RegistrationController(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @GetMapping("/test")
    public ResponseEntity<String> testSys() {
        return ResponseEntity.ok("[play3 project, /test says]: Hello world :)");
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam String username, @RequestParam String email,
                                               @RequestParam String password, Model model, final HttpServletRequest req) throws NoSuchAlgorithmException {
        try {
            String message = registrationService.registerUser(username, email, password);  // checks if username or email already exists are done in the registerUser method
            User user = registrationService.getUser();
            publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUlr(req)));
            if (message.equals("User was saved succesfully."))
                return ResponseEntity.ok("Registration successful. Please go to your email to confirm your membership before loggin in.");
            return ResponseEntity.ok(message);
        } catch (NoSuchAlgorithmException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("from registration controller: " + ex.getMessage());
        }
    }

    private String applicationUlr(HttpServletRequest req) {
        return "http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
    }

    @GetMapping("/verifyEmail")
    public ResponseEntity<String> verifyUserEmail(@RequestParam("token") String token) {
        registrationService.verifyUserToken(token);
        return ResponseEntity.ok("You have successfully verified your email. Now you can login as user");
    }
}
