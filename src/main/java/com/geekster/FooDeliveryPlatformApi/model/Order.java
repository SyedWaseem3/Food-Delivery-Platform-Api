package com.geekster.FooDeliveryPlatformApi.model;

import com.geekster.FooDeliveryPlatformApi.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    @Positive(message = "Order quantity must be a positive number")
    private Integer orderQuantity;
    @Enumerated(EnumType.STRING)
    private Status orderStatusFood;
    private LocalDateTime orderCreationTimeStamp;

    @ManyToOne
            @JoinColumn(name = "fk_user_id")
    User user;

    @OneToOne
            @JoinColumn(name = "fk_food_id")
    Food food;
}
