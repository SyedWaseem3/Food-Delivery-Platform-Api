package com.geekster.FooDeliveryPlatformApi.controller;

import com.geekster.FooDeliveryPlatformApi.model.Food;
import com.geekster.FooDeliveryPlatformApi.model.Order;
import com.geekster.FooDeliveryPlatformApi.model.User;
import com.geekster.FooDeliveryPlatformApi.model.dto.AuthenticationInputDto;
import com.geekster.FooDeliveryPlatformApi.model.dto.PlaceOrderDto;
import com.geekster.FooDeliveryPlatformApi.service.OrderService;
import com.geekster.FooDeliveryPlatformApi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;



    //sign up
    @PostMapping("signUp")
    public String signUp(@RequestBody @Valid User newUser){
        return userService.signUp(newUser);
    }

    //sign in
    @PostMapping("signIn/email/{email}/password/{password}")
    public String signIn(@PathVariable String email, @PathVariable String password){
        return userService.signIn(email, password);
    }


    //sign out
    @DeleteMapping("signOut/email/{email}/tokenValue/{tokenValue}")
    public String signOut(@PathVariable String email, @PathVariable String tokenValue){
        return userService.signOut(email, tokenValue);
    }

    //Place an order
    @PostMapping("placeOrder")
    public String placeOrder(@RequestBody @Valid PlaceOrderDto placeOrderDto){
        return orderService.placeOrder(placeOrderDto.getAuthenticationInputDto(), placeOrderDto.getOrder());
    }


    //Cancel an order
    @DeleteMapping("cancelOrder/orderId/{orderId}")
    public String cancelOrder(@RequestBody @Valid AuthenticationInputDto authenticationInputDto, @PathVariable Integer orderId){
        return orderService.cancelOrder(authenticationInputDto, orderId);
    }

    //Get list of all foods
    @GetMapping("foods")
    public List<Food> getAllFoods(@RequestBody @Valid AuthenticationInputDto authenticationInputDto){
        return userService.getAllFoods(authenticationInputDto);
    }

    //Get all your orders
    @GetMapping("previousOrders/email/{email}/tokenValue/{tokenValue}")
    public List<Order> getAllOrders(@PathVariable String email, @PathVariable String tokenValue){
        return userService.getAllOrders(email, tokenValue);
    }

    //Get order by id
    @GetMapping("orderById/{orderId}/email/{email}/tokenValue/{tokenValue}")
    public Order getOrderById(@PathVariable Integer orderId, @PathVariable String email, @PathVariable String tokenValue){
        return userService.getOrderById(orderId, email, tokenValue);
    }
}
