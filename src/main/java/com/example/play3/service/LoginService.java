package com.example.play3.service;

import com.example.play3.domain.User;
import com.example.play3.repository.UserRepository;
import com.example.play3.utils.security.password.HashService;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.example.play3.utils.security.password.HashAndSaltUtil.getHashFromHashSalt;

@Service
public class LoginService {

    private final UserRepository    userRepository;
    private final HashService       hashService;

    public LoginService(UserRepository userRepository, HashService hashService) {
        this.userRepository = userRepository;
        this.hashService = hashService;
    }
    public boolean authenticateUser(String username, String passsword) throws NoSuchAlgorithmException {
        boolean userCanEnter = false;
        User loginUser = userRepository.findUserByUsername(username).orElse(null);
        if (loginUser != null) {
            String hashedPW = loginUser.getPassword();
            String salt = loginUser.getSalt();
            String toBechecked = getHashFromHashSalt(hashService.hash(passsword, salt));
            if (hashedPW.equals(toBechecked) && loginUser.isVerified()) {
                userCanEnter = true;
            }
        }
        return userCanEnter;
    }

}
