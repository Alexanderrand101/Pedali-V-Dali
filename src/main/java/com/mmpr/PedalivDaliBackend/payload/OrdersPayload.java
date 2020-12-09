package com.mmpr.PedalivDaliBackend.payload;

import com.mmpr.PedalivDaliBackend.model.Order;
import com.mmpr.PedalivDaliBackend.model.Point;
import lombok.Data;

import java.util.List;

@Data
public class OrdersPayload {
    private int count;

    private List<Order> data;
}
