package com.mmpr.PedalivDaliBackend.payload;

import com.mmpr.PedalivDaliBackend.model.OrderStatus;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class OrderDto {

    private String id;

    private Long cityId;

    private Long specificVehicleId;

    private Long pointId;

    private Double price;

    private Date dateFrom;

    private Date dateTo;

    private Boolean isBodyProtect;

    private Boolean isNeedChildChair;

    private OrderStatus orderStatus;
}
