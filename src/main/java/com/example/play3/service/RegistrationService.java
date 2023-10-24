package com.example.play3.service;

import com.example.play3.domain.User;
import com.example.play3.domain.VerificationToken;
import com.example.play3.repository.UserRepository;
import com.example.play3.utils.security.password.HashService;
import com.example.play3.utils.security.password.SaltMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

import static com.example.play3.utils.security.password.HashAndSaltUtil.getHashFromHashSalt;
import static com.example.play3.utils.security.password.HashAndSaltUtil.getSaltFromHashSalt;

@Service
public class RegistrationService {

    private final UserRepository    userRepository;
    private final JwtService        jwtService; // Inject JwtService
    private final HashService       hashService;
    private final SaltMaker         saltMaker;

    @Autowired
    public RegistrationService(UserRepository userRepository, JwtService jwtService, HashService hashService, SaltMaker saltMaker) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.hashService = hashService;
        this.saltMaker = saltMaker;
    }

    public String registerUser(String username, String password, String email) throws NoSuchAlgorithmException {
        if (userRepository.findByUsername(username).isPresent())
            return "User with this username already exists. Try another one";
        if (userRepository.findByEmail(email).isPresent())
            return "User with this email already exists. Try another one";

        String jwtToken = jwtService.getJwtToken();
        String jwtTokenToSave = jwtToken.length() > 500 ? jwtToken.substring(0, 500) : jwtToken;
        String hashedPwd = hashService.hash(password);
        userRepository.save(userWithHashedPassword(password, email, username));
        return "User was saved succesfully.";
    }

    private User userWithHashedPassword(String password, String email, String username) throws NoSuchAlgorithmException {
        String hashedPwd = hashService.hash(password); // returns pwd with salt hash
        String salt = getSaltFromHashSalt(hashedPwd);
        String pwdHash = getHashFromHashSalt(hashedPwd);
        return new User(email, username, pwdHash, salt);
    }

    public void saveUserVerificationToken(User theUser, String token) {
        VerificationToken verificationToken = new VerificationToken(token, theUser.getId());
        userRepository.saveVerficationToken(verificationToken);
    }
}
