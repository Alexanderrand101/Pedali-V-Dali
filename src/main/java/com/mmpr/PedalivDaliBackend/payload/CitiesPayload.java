package com.mmpr.PedalivDaliBackend.payload;

import com.mmpr.PedalivDaliBackend.model.City;
import lombok.Data;

import java.util.List;

@Data
public class CitiesPayload {
    private int count;

    private List<City> data;
}
