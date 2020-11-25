package com.mmpr.PedalivDaliBackend.service;

import com.mmpr.PedalivDaliBackend.payload.CategoryPayload;
import com.mmpr.PedalivDaliBackend.payload.CitiesPayload;
import com.mmpr.PedalivDaliBackend.payload.PointsPayload;
import com.mmpr.PedalivDaliBackend.payload.VehiclePayload;
import com.mmpr.PedalivDaliBackend.repository.CategoriesRepository;
import com.mmpr.PedalivDaliBackend.repository.CityRepository;
import com.mmpr.PedalivDaliBackend.repository.PointRepository;
import com.mmpr.PedalivDaliBackend.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityRepository cityRepository;

    @Autowired
    PointRepository pointRepository;

    @Autowired
    CategoriesRepository categoriesRepository;
    
    @Autowired
    VehicleRepository vehicleRepository;

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

    @Override
    public CategoryPayload getAllCategories() {
        CategoryPayload categoryPayload = new CategoryPayload();
        categoryPayload.setData(categoriesRepository.findAll());
        categoryPayload.setCount(categoryPayload.getData().size());
        return categoryPayload;
    }

    @Override
    public VehiclePayload getAllVehicles() {
        VehiclePayload vehiclePayload = new VehiclePayload();
        vehiclePayload.setData(vehicleRepository.findAll());
        vehiclePayload.setCount(vehiclePayload.getData().size());
        return vehiclePayload;
    }
}
