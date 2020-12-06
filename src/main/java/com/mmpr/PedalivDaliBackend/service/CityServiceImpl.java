package com.mmpr.PedalivDaliBackend.service;

import com.mmpr.PedalivDaliBackend.model.*;
import com.mmpr.PedalivDaliBackend.payload.*;
import com.mmpr.PedalivDaliBackend.repository.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
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

    @Autowired
    UserRepository userRepository;

    private static BigDecimal CHILD_CHAIR = new BigDecimal("200.0");
    private static BigDecimal PROTECTION = new BigDecimal("500.0");
    private static BigDecimal RATE = new BigDecimal("7.0");

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
        Vehicle vehicle = vehicleRepository.findById(orderDto.getVehicleId()).orElseThrow();
        User user = userRepository.findById(orderDto.getUserId()).orElseThrow();
        SpecificVehicle specificVehicle = specificVehicleRepository.findByPointAndVehicleAndVehicleState(point,  vehicle, VehicleState.FREE).orElseThrow();
        specificVehicle.setVehicleState(VehicleState.BUSY);
        specificVehicle = specificVehicleRepository.save(specificVehicle);
        order.setCityId(city);
        order.setPointId(point);
        order.setSpecificVehicleId(specificVehicle);
        order.setDateFrom(orderDto.getDateFrom());
        order.setDateTo(orderDto.getDateTo());
        order.setOrderStatus(OrderStatus.NEW);
        order.setIsBodyProtect(order.getIsBodyProtect());
        order.setIsNeedChildChair(order.getIsNeedChildChair());
        order.setUser(user);
        order.setPrice(calcPrice(order, specificVehicle.getVehicle().getPrice()));
        return orderRepository.save(order);
    }

    @Transactional
    public Order newOrderForSpecificVehicle(OrderDto orderDto){
        Order order = new Order();
        //replace exception
        //City city = cityRepository.findById(orderDto.getCityId()).orElseThrow();
        //Point point = pointRepository.findById(orderDto.getPointId()).orElseThrow();
        SpecificVehicle specificVehicle = specificVehicleRepository.findById(orderDto.getVehicleId()).orElseThrow();
        if (VehicleState.FREE_NOT_AT_POINT.equals(specificVehicle.getVehicleState()) || VehicleState.FREE.equals(specificVehicle.getVehicleState())) {
            User user = userRepository.findById(orderDto.getUserId()).orElseThrow();
            specificVehicle.setVehicleState(VehicleState.BUSY);
            specificVehicle = specificVehicleRepository.save(specificVehicle);
            order.setSpecificVehicleId(specificVehicle);
            order.setDateFrom(new Date());
            order.setDateTo(orderDto.getDateTo());
            order.setOrderStatus(OrderStatus.STARTED);
            order.setIsBodyProtect(false);
            order.setIsNeedChildChair(false);
            order.setUser(user);
            order.setPrice(calcPrice(order, specificVehicle.getVehicle().getPrice()));
            return orderRepository.save(order);
        } else {
            throw new RuntimeException();//will be something
        }
    }

    @Override
    public SpecificVehicle getSpecificVehicle(Long vehicleId) {
        return specificVehicleRepository.findById(vehicleId).orElseThrow();
    }

    //make only for NEW orders
    @Override
    @Transactional
    public Order cancelOrder(String orderId) {
        Order order = orderRepository.findById(UUID.fromString(orderId)).orElseThrow();
        if (order.getOrderStatus().equals(OrderStatus.NEW)){
            order.setOrderStatus(OrderStatus.CANCELED);
            SpecificVehicle specificVehicle = order.getSpecificVehicleId();
            specificVehicle.setVehicleState(VehicleState.FREE);
            specificVehicleRepository.save(specificVehicle);
        }
        return orderRepository.save(order);
    }

    //make only for NEW orders
    @Override
    @Transactional
    public Order finishOrder(String orderId) {
        Order order = orderRepository.findById(UUID.fromString(orderId)).orElseThrow();
        if (order.getOrderStatus().equals(OrderStatus.NEW)) {
            order.setOrderStatus(OrderStatus.FINISHED);
            SpecificVehicle specificVehicle = order.getSpecificVehicleId();
            specificVehicle.setVehicleState(VehicleState.FREE_NOT_AT_POINT);
            specificVehicleRepository.save(specificVehicle);
            Date now = new Date();
            if (now.after(order.getDateTo())) {
                order.setDateTo(now);
                order.setPrice(calcPrice(order, specificVehicle.getVehicle().getPrice()));
            }
        }
        return orderRepository.save(order);
    }

    @Override
    public Order startOrder(String orderId) {
        Order order = orderRepository.findById(UUID.fromString(orderId)).orElseThrow();
        Date now = new Date();
        if (order.getOrderStatus().equals(OrderStatus.NEW) && now.after(order.getDateFrom())) {
            order.setOrderStatus(OrderStatus.STARTED);
        }
        return orderRepository.save(order);
    }

    private BigDecimal calcPrice(Order order, BigDecimal vehiclePrice){
        return vehiclePrice.add(order.getIsNeedChildChair() ? CHILD_CHAIR : new BigDecimal("0.0"))
                .add(order.getIsBodyProtect() ? PROTECTION : new BigDecimal("0.0"))
                .add(RATE.multiply(new BigDecimal(
                        Math.ceil(((double) (order.getDateTo().getTime() - order.getDateFrom().getTime())) / 60000))
                ));
    }

    @Override
    public Order getOrder(String orderId) {
        //replace exception
        return orderRepository.findById(UUID.fromString(orderId)).orElseThrow();
    }
}
