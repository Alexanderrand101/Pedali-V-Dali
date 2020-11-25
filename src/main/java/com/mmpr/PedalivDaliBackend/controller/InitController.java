package com.mmpr.PedalivDaliBackend.controller;

import com.mmpr.PedalivDaliBackend.model.City;
import com.mmpr.PedalivDaliBackend.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InitController {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/init")
    public ResponseEntity<?> init(){
        List<String> cityNames = List.of("Самара", "Тольятти", "Новокуйбышевск");
        List<City> cities = new ArrayList<>();
        for (String name:
             cityNames) {
            City city = new City();
            city.setName(name);
            cities.add(city);
        }
        cityRepository.saveAll(cities);
        return ResponseEntity.ok().build();
    }
}
