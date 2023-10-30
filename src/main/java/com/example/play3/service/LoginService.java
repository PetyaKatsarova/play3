package com.example.play3.service;

import com.example.play3.domain.User;
import com.example.play3.repository.UserRepository;
import com.example.play3.utils.security.password.HashService;
import com.example.play3.utils.security.token.JWTToken;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

import static com.example.play3.utils.security.password.HashAndSaltUtil.getHashFromHashSalt;

@Service
public class LoginService {

    private final UserRepository    userRepository;
    private final HashService       hashService;
    private final JWTToken          jwtToken;
    public LoginService(UserRepository userRepository, HashService hashService, JWTToken jwtToken) {
        this.userRepository = userRepository;
        this.hashService = hashService;
        this.jwtToken = jwtToken;
    }
    public User authenticateUser(String email, String password) throws NoSuchAlgorithmException {
        User loginUser = userRepository.findByEmail(email).orElse(null);
        if (loginUser != null) {
            String hashedPW = loginUser.getPassword();
            String salt = loginUser.getSalt();
            String toBechecked = getHashFromHashSalt(hashService.hash(password, salt));
            if (!(hashedPW.equals(toBechecked) && loginUser.isVerified()))
                return null;
        }
        return loginUser;
    }

    public String generateJWTToken(String email) {
        return jwtToken.generateJWTToken(email);
    }
}
