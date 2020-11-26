package com.mmpr.PedalivDaliBackend.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "specific_vehicle")
@Data
public class SpecificVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "point_id")
    private Point point;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VehicleState vehicleState;
}
