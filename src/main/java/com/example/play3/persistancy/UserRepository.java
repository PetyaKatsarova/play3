package com.example.play3.persistancy;

import com.example.play3.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean         existsByUsername(String username);
    Optional<User>  findByUsername(String username);
}