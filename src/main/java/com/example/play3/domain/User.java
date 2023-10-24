package com.example.play3.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String salt;

    @Column(name = "jwt_token", length = 501)
    private String jwtToken;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private boolean verified;

    @Column(name = "verification_token")
    private String verificationToken;

    @Column(name = "verification_token_expiry_time")
    private Date tokenExpirationTime;

//    @Column(name = "expiration_date")
//    private Date expirationDate;

    public User() {
    }

    public User(String email, String username, String password, String jwtToken, String  salt) {
        System.out.println("JWT Token length: " + jwtToken.length());

        this.username = username;
        this.password = password;
        this.salt = salt;
        this.jwtToken = jwtToken;
        this.email = email;
        this.verified = false;
    }

    public User(String username, String email, String password, String salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.email = email;
        this.verified = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public Date getTokenExpirationTime() {
        return tokenExpirationTime;
    }

    public void setTokenExpirationTime(Date tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }
}

