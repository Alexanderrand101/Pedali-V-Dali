package com.mmpr.PedalivDaliBackend.repository;

import com.mmpr.PedalivDaliBackend.model.Point;
import com.mmpr.PedalivDaliBackend.model.SpecificVehicle;
import com.mmpr.PedalivDaliBackend.model.Vehicle;
import com.mmpr.PedalivDaliBackend.model.VehicleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

public interface SpecificVehicleRepository extends JpaRepository<SpecificVehicle, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    Optional<SpecificVehicle> findByPointAndVehicleAndVehicleState(Point point, Vehicle vehicle, VehicleState vehicleState);
}
