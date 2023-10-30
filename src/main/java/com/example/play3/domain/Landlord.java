package com.example.play3.domain;

import jakarta.persistence.*;

@Entity
@Table //(name="landlord")
public class Landlord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long landlordId;
    @OneToOne(fetch = FetchType.LAZY)     // One-to-One relationship with the User
    @JoinColumn(name = "id", nullable = false)
    private User    user;

    public Landlord(User user) {
        this.user = user;
    }

    public Landlord() {

    }

    public Long getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(Long landlordId) {
        this.landlordId = landlordId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

