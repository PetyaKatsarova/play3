package com.example.play3.utils.security.password;

import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;

@Service
public class HashService {

    private static final int        DEFAULT_ROUNDS = 1;
    private  static final String    PEPPER = "ExtraSmaak";
    private final SaltMaker         saltMaker;
    private final int               rounds;
    private final String            pepper;

//    @Autowired
    public HashService() {
        this(DEFAULT_ROUNDS, PEPPER);
    }

    public HashService(int rounds, String pepper) {
        this(rounds, pepper, SaltMaker.STANDARD_SALT_LENGTH);
    }

    public HashService(int rounds, String pepper, int saltLength) {
        this.rounds = rounds;
        this.pepper = pepper;
        this.saltMaker = new SaltMaker(saltLength);
    }

    public String hash(String password) throws NoSuchAlgorithmException {
        String salt = saltMaker.generateSalt();
        return hash(password, salt);
    }

    public String hash(String password, String salt) throws NoSuchAlgorithmException {
        String hash = HashHelper.hash(password, salt, HashService.PEPPER);
        String processedHash = processRounds(hash, numberOfRounds(rounds));
        return processedHash + salt;
    }

    private String processRounds(String hash, long numberOfRounds) throws NoSuchAlgorithmException {
        for (int i = 0; i < numberOfRounds; i++) {
            hash = HashHelper.hash(hash);
        }
        return hash;
    }

    private long numberOfRounds(int rounds) {
        return rounds;
    }

    public SaltMaker getSaltMaker() {
        return saltMaker;
    }

    public int getRounds() {
        return rounds;
    }

    public String getPepper() {
        return pepper;
    }
}
