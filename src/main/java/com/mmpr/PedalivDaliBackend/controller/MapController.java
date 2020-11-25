package com.mmpr.PedalivDaliBackend.controller;

import com.mmpr.PedalivDaliBackend.exception.ResourceNotFoundException;
import com.mmpr.PedalivDaliBackend.model.City;
import com.mmpr.PedalivDaliBackend.model.Point;
import com.mmpr.PedalivDaliBackend.payload.CitiesPayload;
import com.mmpr.PedalivDaliBackend.payload.PointsPayload;
import com.mmpr.PedalivDaliBackend.repository.CityRepository;
import com.mmpr.PedalivDaliBackend.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MapController {

    @Autowired
    private CityService cityService;

    @GetMapping("/db/city")
    public CitiesPayload getCities(){
        return cityService.getAllCities();
    }

    @GetMapping("/db/point")
    public PointsPayload getPoints(){
        return cityService.getAllPoints();
    }
}
