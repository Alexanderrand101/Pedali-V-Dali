package com.mmpr.PedalivDaliBackend.repository;

import com.mmpr.PedalivDaliBackend.model.Point;
import com.mmpr.PedalivDaliBackend.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT v FROM Vehicle v WHERE EXISTS (SELECT sv FROM SpecificVehicle sv WHERE sv.point.id = :pointId AND sv.vehicleState='FREE' AND sv.vehicle=v)")
    List<Vehicle> findVehiclesAtPoint(Long pointId);
}
