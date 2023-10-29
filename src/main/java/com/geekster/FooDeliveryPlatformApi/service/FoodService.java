package com.geekster.FooDeliveryPlatformApi.service;

import com.geekster.FooDeliveryPlatformApi.model.Admin;
import com.geekster.FooDeliveryPlatformApi.model.Food;
import com.geekster.FooDeliveryPlatformApi.repo.IAdminRepo;
import com.geekster.FooDeliveryPlatformApi.repo.IFoodRepo;
import com.geekster.FooDeliveryPlatformApi.repo.IOrderRepo;
import com.geekster.FooDeliveryPlatformApi.service.hashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class FoodService {
    @Autowired
    IFoodRepo foodRepo;

    @Autowired
    IOrderRepo orderRepo;

    @Autowired
    IAdminRepo adminRepo;

    @Autowired
    AuthenticationService authenticationService;

    public String addFood(Food newFood, String adminEmail, String tokenValue) {

        if(authenticationService.authenticateAdmin(adminEmail, tokenValue)){
            if(adminEmail.endsWith("@admin.com")){
                String foodName = newFood.getFoodName();

                Food existingFood = foodRepo.findFirstByFoodName(foodName);

                if(existingFood != null){
                    return "Food already exists, go to update food section please!";
                }

                foodRepo.save(newFood);
                return newFood.getFoodName() + " food has been added to the menu!";
            }else {
                return "Only admins can add food!";
            }
        }else{
            return "Un authenticated access!!";
        }

    }

    public List<Food> getAllFoods(String adminEmail, String tokenValue) {

        if(authenticationService.authenticateAdmin(adminEmail, tokenValue)){
            if(adminEmail.endsWith("@admin.com")){
                return foodRepo.findAll();
            }
        }
        return null;
    }

    public String updateFoodByName(String name, Integer price, String adminEmail, String tokenValue) {

        if(authenticationService.authenticateAdmin(adminEmail, tokenValue)){
            if(adminEmail.endsWith("@admin.com")){
                Food existingFood = foodRepo.findFirstByFoodName(name);

                if(existingFood == null){
                    return "Food was not found, check the spelling mistake of the food or add new food!";
                }

                existingFood.setFoodPrice(price);
                foodRepo.save(existingFood);
                return existingFood.getFoodName() + " price has been updated!";
            }else{
                return "Only admins can update the food!";
            }
        }else{
            return "Un authenticated access!";
        }
    }

    public String deleteFoodById(Integer foodId, String adminEmail, String tokenValue) {

        if(authenticationService.authenticateAdmin(adminEmail, tokenValue)){
            if(adminEmail.endsWith("@admin.com")){
                Food food = foodRepo.findById(foodId).orElseThrow();

                foodRepo.delete(food);
                return food.getFoodName()+ " food deleted!!";
            }else{
                return "Only admins can delete the food!!";
            }
        }else{
            return "Un authenticated access!!";
        }
    }
}
