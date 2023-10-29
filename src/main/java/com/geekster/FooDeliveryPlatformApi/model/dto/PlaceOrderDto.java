package com.geekster.FooDeliveryPlatformApi.model.dto;

import com.geekster.FooDeliveryPlatformApi.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderDto {
    AuthenticationInputDto authenticationInputDto;
    Order order;
}
