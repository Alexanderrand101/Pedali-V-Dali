package com.mmpr.PedalivDaliBackend.service;

import com.mmpr.PedalivDaliBackend.model.*;
import com.mmpr.PedalivDaliBackend.payload.*;
import com.mmpr.PedalivDaliBackend.repository.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    SpecificVehicleRepository specificVehicleRepository;

    private static double CHILD_CHAIR = 200.0;
    private static double PROTECTION = 500.0;
    private static double RATE = 7.0;

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

    @Override
    public VehiclePayload getAllVehiclesAtPoint(Long pointId) {
        VehiclePayload vehiclePayload = new VehiclePayload();
        vehiclePayload.setData(vehicleRepository.findVehiclesAtPoint(pointId));
        vehiclePayload.setCount(vehiclePayload.getData().size());
        return vehiclePayload;
    }

    @Transactional
    public Order newOrder(OrderDto orderDto){
        Order order = new Order();
        //replace exception
        City city = cityRepository.findById(orderDto.getCityId()).orElseThrow();
        Point point = pointRepository.findById(orderDto.getPointId()).orElseThrow();
        SpecificVehicle specificVehicle = specificVehicleRepository.findByIdAndAndVehicleState(orderDto.getSpecificVehicleId(), VehicleState.FREE).orElseThrow();
        order.setCityId(city);
        order.setPointId(point);
        order.setSpecificVehicleId(specificVehicle);
        order.setDateFrom(orderDto.getDateFrom());
        order.setDateTo(orderDto.getDateTo());
        order.setOrderStatus(OrderStatus.NEW);
        order.setIsBodyProtect(order.getIsBodyProtect());
        order.setIsNeedChildChair(order.getIsNeedChildChair());
        order.setPrice(calcPrice(orderDto, specificVehicle.getVehicle().getPriceMin()));
        return order;
    }

    @Override
    public Order cancelOrder(String orderId) {
        Order order = orderRepository.findById(UUID.fromString(orderId)).orElseThrow();
        order.setOrderStatus(OrderStatus.CANCELED);
        return orderRepository.save(order);
    }

    @Override
    public Order finishOrder(String orderId) {
        Order order = orderRepository.findById(UUID.fromString(orderId)).orElseThrow();
        order.setOrderStatus(OrderStatus.FINISHED);
        return orderRepository.save(order);
    }

    private double calcPrice(OrderDto orderDto, Double vehiclePrice){
        return (orderDto.getIsNeedChildChair() ? CHILD_CHAIR : 0.0) +
                (orderDto.getIsBodyProtect() ? PROTECTION : 0.0) + vehiclePrice +
                Math.ceil(((double) (orderDto.getDateTo().getTime() - orderDto.getDateFrom().getTime())) / 60000) * RATE;
    }

    @Override
    public Order getOrder(String orderId) {
        //replace exception
        return orderRepository.findById(UUID.fromString(orderId)).orElseThrow();
    }
}
