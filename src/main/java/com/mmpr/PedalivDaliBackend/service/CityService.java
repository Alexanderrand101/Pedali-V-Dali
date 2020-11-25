package com.mmpr.PedalivDaliBackend.service;

import com.mmpr.PedalivDaliBackend.payload.CitiesPayload;
import com.mmpr.PedalivDaliBackend.payload.PointsPayload;

public interface CityService {

    CitiesPayload getAllCities();

    PointsPayload getAllPoints();
}
