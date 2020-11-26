package com.mmpr.PedalivDaliBackend.controller;

import com.mmpr.PedalivDaliBackend.exception.ResourceNotFoundException;
import com.mmpr.PedalivDaliBackend.model.City;
import com.mmpr.PedalivDaliBackend.model.Order;
import com.mmpr.PedalivDaliBackend.model.Point;
import com.mmpr.PedalivDaliBackend.payload.*;
import com.mmpr.PedalivDaliBackend.repository.CityRepository;
import com.mmpr.PedalivDaliBackend.repository.OrderRepository;
import com.mmpr.PedalivDaliBackend.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MapController {

    @Autowired
    private CityService cityService;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/db/city")
    public CitiesPayload getCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/db/point")
    public PointsPayload getPoints() {
        return cityService.getAllPoints();
    }

    @GetMapping("/db/category")
    public CategoryPayload getCategories() {
        return cityService.getAllCategories();
    }

    @GetMapping("/db/vehicle/atPoint/{pointId}")
    public VehiclePayload getVehiclesAtPoint(@PathVariable Long pointId) {
        return cityService.getAllVehiclesAtPoint(pointId);
    }

    @GetMapping("/db/vehicle")
    public VehiclePayload getVehicles() {
        return cityService.getAllVehicles();
    }

    @GetMapping("/db/order")
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/db/order/{orderId}")
    public Order getOrder(@PathVariable String orderId) {
        return cityService.getOrder(orderId);
    }

    @PostMapping("/db/order/new")
    public Order newOrder(@RequestBody OrderDto orderDto) {
        return cityService.newOrder(orderDto);
    }

    @GetMapping("/db/order/cancel/{id}")
    public Order cancelOrder(@PathVariable String orderId) {
        return cityService.cancelOrder(orderId);
    }

    @GetMapping("/db/order/finish/{id}")
    public Order finishOrder(@PathVariable String orderId) {
        return cityService.finishOrder(orderId);
    }
}
