package com.example.play3.service;

import com.example.play3.domain.House;
import com.example.play3.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;

    public House saveOrUpdateHouse(House house) {
        return houseRepository.save(house);
    }

    public House findHouseById(Long houseId) {
        return houseRepository.findById(houseId).orElse(null);  // or handle exceptions
    }
}

