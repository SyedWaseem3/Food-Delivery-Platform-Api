package com.geekster.FooDeliveryPlatformApi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.web.servlet.filter.OrderedRequestContextFilter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "foods")

public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer foodId;
    @NotBlank(message = "Food name is required")
    private String foodName;

    @NotBlank(message = "Food description is required")
    private String foodDescription;

    @Positive(message = "Food price must be a positive number")
    private Integer foodPrice;
}
