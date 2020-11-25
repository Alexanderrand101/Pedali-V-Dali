package com.mmpr.PedalivDaliBackend.service;

import com.mmpr.PedalivDaliBackend.payload.CitiesPayload;
import org.springframework.stereotype.Service;

public interface CityService {

    CitiesPayload getAllCities();
}
