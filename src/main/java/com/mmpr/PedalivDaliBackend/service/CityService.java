package com.mmpr.PedalivDaliBackend.service;

import com.mmpr.PedalivDaliBackend.model.Order;
import com.mmpr.PedalivDaliBackend.model.SpecificVehicle;
import com.mmpr.PedalivDaliBackend.payload.*;

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

    OrdersPayload getAllOrdersForUser(Long id);
}
