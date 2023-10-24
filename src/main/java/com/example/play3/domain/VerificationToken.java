package com.example.play3.domain;

import jakarta.persistence.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "verification_token")
public class VerificationToken {
//    private final Logger logger = LoggerFactory.getLogger(VerificationToken.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(name = "expiration_time")
    @Temporal(TemporalType.DATE)
    private Date expirationTime; // change to time not date

    @Column(nullable = false)
    private long userid;

    private static final int EXPIRATION_TIME = 15;

    public VerificationToken() {}

    public VerificationToken(String token) {
        super();
        this.token = token;
        this.expirationTime = this.getTokenExpirationTime();
    }

    public VerificationToken(String token, long userId) {
        super();
        this.token = token;
        this.userId = userId;
        this.expirationTime = this.getTokenExpirationTime();
    }

    public VerificationToken(String token, Date expirationTime) {
        this.token = token;
        this.expirationTime = expirationTime;
    }

    public Date getTokenExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
        return calendar.getTime();
    }

}
