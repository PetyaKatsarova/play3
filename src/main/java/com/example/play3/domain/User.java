package com.example.play3.domain;

import com.example.play3.utils.security.password.SaltMaker;
import jakarta.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column()
    private boolean isVerified;
    @Column()
    private String verificationToken;
    @Column()
    private Date tokenExpirationTime;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String salt;
    private static int EXPIRATION_TIME = 15;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.salt = new SaltMaker().generateSalt();
        this.isVerified = false;
        this.tokenExpirationTime = createTokenExpirationTime();
        this.verificationToken = UUID.randomUUID().toString();
    }

    public User(String username, String email, String password, String salt) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.isVerified = false;
        this.tokenExpirationTime = createTokenExpirationTime();
        this.verificationToken = UUID.randomUUID().toString();
    }

    private Date createTokenExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
        return calendar.getTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User user)) {
            return false;
        }
        return Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPassword(), getEmail());
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", salt='" + salt + '\'' +
                ", verificationToken='" + verificationToken + '\'' +
                ", tokenExpirationTime='" + tokenExpirationTime + '\'' +
                '}';
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

    public Date getTokenExpirationTime() {
        return tokenExpirationTime;
    }
}

