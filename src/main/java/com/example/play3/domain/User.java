package com.example.play3.domain;

import jakarta.persistence.*;

import java.util.Calendar;
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

    @Column(name = "is_verified")
    private boolean isVerified;

    @Column(name = "verification_token")
    private String verificationToken;

    @Column(name = "verification_token_expiry_time")
    private Date tokenExpirationTime;

    private static int EXPIRATION_TIME = 15;

    public User() {
    }

//    public User(String username, String email, String password, String jwtToken, String  salt) {
//        System.out.println("JWT Token length: " + jwtToken.length());
//
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.salt = salt;
//        this.jwtToken = jwtToken;
//        this.isVerified = false;
//        this.tokenExpirationTime = null;
//        this.verificationToken = null;
//    }

    public User(String username, String email, String password, String salt) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.isVerified = false;
        this.tokenExpirationTime = null;
        this.verificationToken = null;
    }

    public Date createTokenExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
        return calendar.getTime();
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
        return isVerified;
    }

    public void setVerified(boolean verified) {
        this.isVerified = verified;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public void setTokenExpirationTime(Date tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }

    public void setExpirationTimeInMinutes(int minutes) {
        EXPIRATION_TIME = minutes;
    }

}

