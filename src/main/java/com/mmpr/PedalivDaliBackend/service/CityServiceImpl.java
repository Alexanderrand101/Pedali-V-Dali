package com.mmpr.PedalivDaliBackend.service;

import com.mmpr.PedalivDaliBackend.payload.CitiesPayload;
import com.mmpr.PedalivDaliBackend.payload.PointsPayload;
import com.mmpr.PedalivDaliBackend.repository.CityRepository;
import com.mmpr.PedalivDaliBackend.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityRepository cityRepository;

    @Autowired
    PointRepository pointRepository;

    @Override
    public CitiesPayload getAllCities() {
        CitiesPayload citiesPayload = new CitiesPayload();
        citiesPayload.setData(cityRepository.findAll());
        citiesPayload.setCount(citiesPayload.getData().size());
        return citiesPayload;
    }

    @Override
    public PointsPayload getAllPoints() {
        PointsPayload pointPayload = new PointsPayload();
        pointPayload.setData(pointRepository.findAll());
        pointPayload.setCount(pointPayload.getData().size());
        return pointPayload;
    }
}
