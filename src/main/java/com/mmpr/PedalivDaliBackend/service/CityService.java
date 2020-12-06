package com.mmpr.PedalivDaliBackend.service;

import com.mmpr.PedalivDaliBackend.model.Order;
import com.mmpr.PedalivDaliBackend.model.SpecificVehicle;
import com.mmpr.PedalivDaliBackend.payload.*;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface CityService {

    CitiesPayload getAllCities();

    PointsPayload getAllPoints();

    CategoryPayload getAllCategories();

    VehiclePayload getAllVehicles();

    VehiclePayload getAllVehiclesAtPoint(Long pointId);

    Order getOrder(String orderId);

    Order newOrder(OrderDto orderDto);

    Order cancelOrder(String orderId);

    Order finishOrder(String orderId);

    Order startOrder(String orderId);

    Order newOrderForSpecificVehicle(OrderDto orderDto);

    SpecificVehicle getSpecificVehicle(Long vehicleId);
}
