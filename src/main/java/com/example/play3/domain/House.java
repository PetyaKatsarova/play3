package com.example.play3.domain;

import jakarta.persistence.*;

@Entity
@Table(name="house")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long houseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "landlordId") // fk
    private Landlord landlord;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String number;

    private String addition;  // Optional, since it was not marked as required in the form

    @Column(nullable = false)
    private Integer numberOfPeople;

    @Column(nullable = false)
    private Integer numberOfRooms;

    @Column(nullable = false)
    private Double pricePerPersonPerNight;

    public House(Long houseId, Landlord landlord, String code, String street, String number, String addition, Integer numberOfPeople, Integer numberOfRooms, Double pricePerPersonPerNight) {
        this.houseId = houseId;
        this.landlord = landlord;
        this.postalCode = code;
        this.street = street;
        this.number = number;
        this.addition = addition;
        this.numberOfPeople = numberOfPeople;
        this.numberOfRooms = numberOfRooms;
        this.pricePerPersonPerNight = pricePerPersonPerNight;
    }

    public House() {

    }
}
