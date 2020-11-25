package com.mmpr.PedalivDaliBackend.controller;

import com.mmpr.PedalivDaliBackend.model.*;
import com.mmpr.PedalivDaliBackend.repository.*;
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

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private ThumbnailRepository thumbnailRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping("/init")
    public ResponseEntity<?> init(){
        vehicleRepository.deleteAll();
        categoriesRepository.deleteAll();
        thumbnailRepository.deleteAll();
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
        point1.setCityId(cityRepository.findAllByName("Самара").get(0));
        point1.setName("КосмопортНоНеПорт");
        Point point2 = new Point();
        point2.setAddress("проспект Карла Маркса 201");
        point2.setCityId(cityRepository.findAllByName("Самара").get(0));
        point2.setName("Дом печати");
        pointRepository.save(point1);
        pointRepository.save(point2);
        Category category1 = new Category();
        category1.setName("Велосипеды");
        category1 = categoriesRepository.save(category1);
        Category category2 = new Category();
        category2.setName("Самокаты");
        category2 = categoriesRepository.save(category2);
        Thumbnail thumbnail1 = new Thumbnail();
        thumbnail1.setPath("https://i.ibb.co/G770tJS/bike3.jpg");
        thumbnail1 = thumbnailRepository.save(thumbnail1);
        Thumbnail thumbnail2 = new Thumbnail();
        thumbnail2.setPath("https://i.ibb.co/Rp9B7KS/bike2.jpg");
        thumbnail2 = thumbnailRepository.save(thumbnail2);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setName("Хороший велосипед");
        vehicle1.setCategoryId(category1);
        vehicle1.setPoint(point1);
        vehicle1.setPriceMin(100.0);
        vehicle1.setPriceMax(100.0);
        vehicle1.setThumbnail(thumbnail1);
        vehicleRepository.save(vehicle1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setName("Краденый велосипед");
        vehicle2.setCategoryId(category1);
        vehicle2.setPoint(point1);
        vehicle2.setPriceMin(200.0);
        vehicle2.setPriceMax(200.0);
        vehicle2.setThumbnail(thumbnail2);
        vehicleRepository.save(vehicle2);

        return ResponseEntity.ok().build();
    }
}
