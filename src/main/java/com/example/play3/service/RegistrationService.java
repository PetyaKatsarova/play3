package com.example.play3.service;

import com.example.play3.domain.User;
import com.example.play3.repository.UserRepository;
import com.example.play3.utils.security.password.HashService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Date;

import static com.example.play3.utils.security.password.HashAndSaltUtil.getHashFromHashSalt;
import static com.example.play3.utils.security.password.HashAndSaltUtil.getSaltFromHashSalt;

@Service
public class RegistrationService {

    private final UserRepository    userRepository;
    private final HashService       hashService;
    private User                    user;

    @Autowired
    public RegistrationService(UserRepository userRepository, HashService hashService) {
        this.userRepository = userRepository;
        this.hashService = hashService;
        this.user = null;
    }

    public String registerUser(String username, String email, String password) throws NoSuchAlgorithmException {
        if (userRepository.findUserByUsername(username).isPresent())
            return "User with this username already exists. Try another one";
        if (userRepository.findByEmail(email).isPresent())
            return "User with this email already exists. Try another one";
        this.user = userWithHashedPassword(username, email, password);
        System.out.println("--------------registrationService:register user: " + this.user);
        userRepository.save(this.user);
        return "User was saved succesfully.";
    }

    private User userWithHashedPassword(String username, String email, String password) throws NoSuchAlgorithmException {
        String hashedPwd = hashService.hash(password); // returns pwd with salt hash
        String salt = getSaltFromHashSalt(hashedPwd);
        String pwdHash = getHashFromHashSalt(hashedPwd);
        return new User(username, email, pwdHash, salt);
    }

    @Transactional
    public void verifyUserToken(String token) throws IllegalArgumentException {
        try {
            User user = userRepository.findByVerificationToken(token).orElse(null);
            System.out.println("registr.service: verifyusertoken: "+ user);

            if (user != null) {
                System.out.println(user.getTokenExpirationTime());
                if (new Date().after(user.getTokenExpirationTime())) {
                    throw new IllegalArgumentException("Verification token has expired.");
                }
                user.setVerified(true);
                userRepository.save(user);
            } else {
                throw new IllegalArgumentException("Invalid verification token from registrationservice, verifyusertoken.");
            }

        } catch (Exception ex) {
            System.err.println("Error during email verification: " + ex.getMessage());
            throw ex; // Rethrow the exception to the caller if desired
        }
    }
    public User getUser() {
        return user;
    }
}
