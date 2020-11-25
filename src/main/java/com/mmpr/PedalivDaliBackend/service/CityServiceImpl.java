package com.mmpr.PedalivDaliBackend.service;

import com.mmpr.PedalivDaliBackend.payload.CitiesPayload;
import com.mmpr.PedalivDaliBackend.repository.CityRepository;
import com.mmpr.PedalivDaliBackend.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityRepository cityRepository;

    @Override
    public CitiesPayload getAllCities() {
        CitiesPayload citiesPayload = new CitiesPayload();
        citiesPayload.setData(cityRepository.findAll());
        citiesPayload.setCount(citiesPayload.getData().size());
        return citiesPayload;
    }
}
