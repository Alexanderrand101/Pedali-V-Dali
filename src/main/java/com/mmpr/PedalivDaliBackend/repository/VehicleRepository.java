package com.mmpr.PedalivDaliBackend.repository;

import com.mmpr.PedalivDaliBackend.model.Point;
import com.mmpr.PedalivDaliBackend.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
