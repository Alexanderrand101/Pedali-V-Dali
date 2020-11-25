package com.mmpr.PedalivDaliBackend.payload;

import com.mmpr.PedalivDaliBackend.model.City;
import com.mmpr.PedalivDaliBackend.model.Vehicle;
import lombok.Data;

import java.util.List;

@Data
public class VehiclePayload {
    private int count;

    private List<Vehicle> data;
}
