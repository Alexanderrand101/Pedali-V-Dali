package com.mmpr.PedalivDaliBackend.payload;

import com.mmpr.PedalivDaliBackend.model.Category;
import com.mmpr.PedalivDaliBackend.model.City;
import lombok.Data;

import java.util.List;

@Data
public class CategoryPayload {
    private int count;

    private List<Category> data;
}
