package com.mmpr.PedalivDaliBackend.controller;

import com.mmpr.PedalivDaliBackend.model.Order;
import com.mmpr.PedalivDaliBackend.model.SpecificVehicle;
import com.mmpr.PedalivDaliBackend.payload.*;
import com.mmpr.PedalivDaliBackend.repository.OrderRepository;
import com.mmpr.PedalivDaliBackend.service.CityService;
import com.mmpr.PedalivDaliBackend.service.CurrentUser;
import com.mmpr.PedalivDaliBackend.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('USER')")
    public Order newOrder(@RequestBody OrderDto orderDto, @CurrentUser CustomUserDetailsService.UserPrincipal userPrincipal) {
        orderDto.setUserId(userPrincipal.getId());
        return cityService.newOrder(orderDto);
    }

    @GetMapping("/db/order/cancel/{orderId}")
    @PreAuthorize("hasRole('USER')")
    public Order cancelOrder(@PathVariable String orderId) {
        return cityService.cancelOrder(orderId);
    }

    @GetMapping("/db/order/finish/{orderId}")
    @PreAuthorize("hasRole('USER')")
    public Order finishOrder(@PathVariable String orderId) {
        return cityService.finishOrder(orderId);
    }

    @GetMapping("/db/order/start/{orderId}")
    @PreAuthorize("hasRole('USER')")
    public Order startOrder(@PathVariable String orderId) {
        return cityService.startOrder(orderId);
    }

    @GetMapping("/db/orders")
    @PreAuthorize("hasRole('USER')")
    public OrdersPayload getOrders(@PathVariable String orderId, @CurrentUser CustomUserDetailsService.UserPrincipal userPrincipal) {
        return cityService.getAllOrdersForUser(userPrincipal.getId());
    }

    @GetMapping("/db/specificVehicle/{vehicleId}")
    public SpecificVehicle getForVehicle(@PathVariable Long vehicleId) {
        return cityService.getSpecificVehicle(vehicleId);
    }

    @PostMapping("/db/order/newForVehicle")
    @PreAuthorize("hasRole('USER')")
    public Order startOrderForSpecificVehicle(@RequestBody OrderDto orderDto, @CurrentUser CustomUserDetailsService.UserPrincipal userPrincipal){
        orderDto.setUserId(userPrincipal.getId());
        return cityService.newOrderForSpecificVehicle(orderDto);
    }
}
