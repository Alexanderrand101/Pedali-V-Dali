package com.mmpr.PedalivDaliBackend.model;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "specific_vehicle_id")
    private SpecificVehicle specificVehicleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City cityId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "point_id")
    private Point pointId;

    //replaceWithDecimal
    private Double price;

    private Date dateFrom;

    private Date dateTo;

    @Column(name = "is_body_protect")
    private Boolean isBodyProtect;

    @Column(name = "is_need_child_chair")
    private Boolean isNeedChildChair;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;
}
