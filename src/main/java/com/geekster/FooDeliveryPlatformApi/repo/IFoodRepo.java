package com.geekster.FooDeliveryPlatformApi.repo;

import com.geekster.FooDeliveryPlatformApi.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodRepo extends JpaRepository<Food, Integer> {
    Food findFirstByFoodName(String foodName);

}
