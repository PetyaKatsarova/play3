package com.example.play3.domain;

import jakarta.persistence.*;

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

    // Getters, setters, constructors, etc. go here

    public User() {
        // Default constructor
    }

    public User(String username, String password, String jwtToken, String email) {
        System.out.println("JWT Token length: " + jwtToken.length());

        this.username = username;
        this.password = password;
        this.salt = password + "_salted_with_" + "username";
        this.jwtToken = jwtToken;
        this.email = email;
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
}

