package com.example.play3.repository;

import com.example.play3.domain.House;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {
    // Additional custom queries (if any) can be added here.
}

