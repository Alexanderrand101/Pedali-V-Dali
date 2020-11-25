package com.mmpr.PedalivDaliBackend.service;

import com.mmpr.PedalivDaliBackend.payload.CategoryPayload;
import com.mmpr.PedalivDaliBackend.payload.CitiesPayload;
import com.mmpr.PedalivDaliBackend.payload.PointsPayload;
import com.mmpr.PedalivDaliBackend.payload.VehiclePayload;

public interface CityService {

    CitiesPayload getAllCities();

    PointsPayload getAllPoints();

    CategoryPayload getAllCategories();

    VehiclePayload getAllVehicles();
}
