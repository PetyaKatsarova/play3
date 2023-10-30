package com.example.play3.communication.controller;

import com.example.play3.domain.House;
import com.example.play3.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/houses")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @PostMapping("/save")
    public ResponseEntity<House> saveOrUpdateHouse(@RequestBody House house) {
        return ResponseEntity.ok(houseService.saveOrUpdateHouse(house));
    }

    @GetMapping("/{id}")
    public ResponseEntity<House> getHouseById(@PathVariable Long id) {
        return ResponseEntity.ok(houseService.findHouseById(id));
    }
}

