package com.geekster.FooDeliveryPlatformApi.repo;

import com.geekster.FooDeliveryPlatformApi.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findFirstByTokenValue(String tokenValue);

}
