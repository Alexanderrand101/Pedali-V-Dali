package com.mmpr.PedalivDaliBackend.controller;

import com.mmpr.PedalivDaliBackend.model.City;
import com.mmpr.PedalivDaliBackend.model.Point;
import com.mmpr.PedalivDaliBackend.repository.CityRepository;
import com.mmpr.PedalivDaliBackend.repository.PointRepository;
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

    @Autowired
    private PointRepository pointRepository;

    @GetMapping("/init")
    public ResponseEntity<?> init(){
        pointRepository.deleteAll();
        cityRepository.deleteAll();
        List<String> cityNames = List.of("Самара", "Тольятти", "Новокуйбышевск");
        List<City> cities = new ArrayList<>();
        for (String name:
             cityNames) {
            City city = new City();
            city.setName(name);
            cities.add(city);
        }
        cityRepository.saveAll(cities);
        Point point1 = new Point();
        point1.setAddress("улица Дыбенко, 30");
        point1.setCity(cityRepository.findAllByName("Самара").get(0));
        point1.setName("КосмопортНоНеПорт");
        Point point2 = new Point();
        point2.setAddress("проспект Карла Маркса 201");
        point2.setCity(cityRepository.findAllByName("Самара").get(0));
        point2.setName("Дом печати");
        pointRepository.save(point1);
        pointRepository.save(point2);
        return ResponseEntity.ok().build();
    }
}
