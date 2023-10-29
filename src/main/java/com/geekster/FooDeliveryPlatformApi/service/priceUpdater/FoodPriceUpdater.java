package com.geekster.FooDeliveryPlatformApi.service.priceUpdater;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodPriceUpdater {
    private String name;
    private Integer price;
}
