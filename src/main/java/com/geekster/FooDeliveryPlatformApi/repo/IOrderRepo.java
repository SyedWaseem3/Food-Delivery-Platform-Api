package com.geekster.FooDeliveryPlatformApi.repo;

import com.geekster.FooDeliveryPlatformApi.model.Order;
import com.geekster.FooDeliveryPlatformApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepo extends JpaRepository<Order, Integer> {
    Order findFirstByOrderId(Integer orderId);

    List<Order> findByUserOrderByOrderCreationTimeStampDesc(User user);

}
