package com.mmpr.PedalivDaliBackend.repository;

import com.mmpr.PedalivDaliBackend.model.SpecificVehicle;
import com.mmpr.PedalivDaliBackend.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecificVehicleRepository extends JpaRepository<SpecificVehicle, Long> {
}
