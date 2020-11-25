package com.mmpr.PedalivDaliBackend.payload;

import com.mmpr.PedalivDaliBackend.model.Point;
import lombok.Data;

import java.util.List;

@Data
public class PointsPayload {
    private int count;

    private List<Point> data;
}
