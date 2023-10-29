package com.geekster.FooDeliveryPlatformApi.repo;

import com.geekster.FooDeliveryPlatformApi.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepo extends JpaRepository<Admin, Integer> {

    Admin findFirstByAdminEmail(String email);

}
