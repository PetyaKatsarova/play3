package com.example.play3.service;

import com.example.play3.domain.User;
import com.example.play3.repository.UserRepository;
import com.example.play3.utils.security.password.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;
import static com.example.play3.utils.security.password.HashAndSaltUtil.getHashFromHashSalt;
import static com.example.play3.utils.security.password.HashAndSaltUtil.getSaltFromHashSalt;

@Service
public class RegistrationService {

    private final UserRepository    userRepository;
    private final JwtService        jwtService; // Inject JwtService
    private final HashService       hashService;

    @Autowired
    public RegistrationService(UserRepository userRepository, JwtService jwtService, HashService hashService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.hashService = hashService;
    }

    public String registerUser(String username, String email, String password) throws NoSuchAlgorithmException {
        if (userRepository.findByUsername(username).isPresent())
            return "User with this username already exists. Try another one";
        if (userRepository.findByEmail(email).isPresent())
            return "User with this email already exists. Try another one";

//        String jwtToken = jwtService.getJwtToken();
//        String jwtTokenToSave = jwtToken.length() > 500 ? jwtToken.substring(0, 500) : jwtToken;

        User userToBeSaved = userWithHashedPassword(username, email, password);
        setAndSaveUserVerificationToken(userToBeSaved);
        return "User was saved succesfully.";
    }

    private void setAndSaveUserVerificationToken(User user) {
        String token = UUID.randomUUID().toString(); // in later stage will replace with jwttoken: it is more secure
        user.setVerificationToken(token);

        Date tokenExpirationTime = user.createTokenExpirationTime();
        user.setTokenExpirationTime(tokenExpirationTime);

        userRepository.save(user);
    }


    private User userWithHashedPassword(String username, String email, String password) throws NoSuchAlgorithmException {
        String hashedPwd = hashService.hash(password); // returns pwd with salt hash
        String salt = getSaltFromHashSalt(hashedPwd);
        String pwdHash = getHashFromHashSalt(hashedPwd);
        return new User(username, email, pwdHash, salt);
    }
}
