package com.example.play3.domain;

import jakarta.persistence.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.Date;

@Entity
@Table(name = "verification_token")
public class VerificationToken {
    private final Logger logger = LoggerFactory.getLogger(VerificationToken.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(name = "expiration_date")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;
}
