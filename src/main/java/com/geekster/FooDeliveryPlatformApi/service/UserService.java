package com.geekster.FooDeliveryPlatformApi.service;

import com.geekster.FooDeliveryPlatformApi.model.AuthenticationToken;
import com.geekster.FooDeliveryPlatformApi.model.Food;
import com.geekster.FooDeliveryPlatformApi.model.Order;
import com.geekster.FooDeliveryPlatformApi.model.User;
import com.geekster.FooDeliveryPlatformApi.model.customExceptions.UnauthorizedException;
import com.geekster.FooDeliveryPlatformApi.model.dto.AuthenticationInputDto;
import com.geekster.FooDeliveryPlatformApi.repo.IFoodRepo;
import com.geekster.FooDeliveryPlatformApi.repo.IOrderRepo;
import com.geekster.FooDeliveryPlatformApi.repo.IUserRepo;
import com.geekster.FooDeliveryPlatformApi.service.emailUtility.EmailHandler;
import com.geekster.FooDeliveryPlatformApi.service.hashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    IFoodRepo foodRepo;

    @Autowired
    IOrderRepo orderRepo;

    public String signUp(User newUser) {
        String email = newUser.getUserEmail();

        User existingUser = userRepo.findFirstByUserEmail(email);

        if(existingUser != null){
            return "email already exists, continue with sign in!!";
        }

        String password = newUser.getUserPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);
            newUser.setUserPassword(encryptedPassword);
            userRepo.save(newUser);
            return "User "+newUser.getUserName() + " registered!";
        } catch (NoSuchAlgorithmException e) {
            return "Internal server error, please try again after sometime!";
        }
    }

    public String signIn(String email, String password) {

        User existingUser = userRepo.findFirstByUserEmail(email);

        if(existingUser == null){
            return "Invalid email, please sign up first and then try sign in again!";
        }

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);
            if(existingUser.getUserPassword().equals(encryptedPassword)){
                AuthenticationToken token = new AuthenticationToken(existingUser);
                if(EmailHandler.sendEmail(email, "otp after sign in", token.getTokenValue())){
                    authenticationService.create(token);
                }else{
                    return "Error while generating token!!";
                }
                return "check your email for token!";
            }else{
                return "Invalid credentials!";
            }
        } catch (NoSuchAlgorithmException e) {
            return "Internal server error while saving the password!!";
        }
    }

    public String signOut(String email, String tokenValue) {
        if(authenticationService.authenticate(email, tokenValue)){
            authenticationService.deleteToken(tokenValue);
            return "Sign out successful!!";
        }else{
            return "Un authenticated access!!";
        }
    }

    public List<User> getAllUsers(String adminEmail, String tokenValue) {
        if(authenticationService.authenticateAdmin(adminEmail, tokenValue)) {
            if (adminEmail.endsWith("@admin.com")) {
                return userRepo.findAll();
            }
        }
        return null;
    }

    public List<Food> getAllFoods(AuthenticationInputDto authenticationInputDto) {
        String email = authenticationInputDto.getEmail();
        String tokenValue = authenticationInputDto.getTokenValue();
        if(authenticationService.authenticate(email, tokenValue)) {
            return foodRepo.findAll();
        }
        return null;
    }

    public List<Order> getAllOrders(String email,String tokenValue) {
        if(authenticationService.authenticate(email, tokenValue)){
            User user = userRepo.findFirstByUserEmail(email);
            List<Order> previousOrders = orderRepo.findByUserOrderByOrderCreationTimeStampDesc(user);
            for (Order order : previousOrders){
                order.setUser(null);
            }
            return previousOrders;
        }else{
            return null;
        }
    }

    public Order getOrderById(Integer orderId, String email, String tokenValue) {
        if(authenticationService.authenticate(email, tokenValue)){
            Order order = orderRepo.findFirstByOrderId(orderId);
            User user = userRepo.findFirstByUserEmail(email);
            if(order.getUser().equals(user)){
                order.setUser(null);
                return order;
            }else{
                throw new UnauthorizedException("You are not able to see others orders!!");
            }
        }else{
            throw new UnauthorizedException("Un Authenticated Access!!");
        }
    }

    public User getUserById(Integer userId, String adminEmail, String tokenValue) {
        if(authenticationService.authenticateAdmin(adminEmail, tokenValue)){
            if (adminEmail.endsWith("@admin.com")) {
                return userRepo.findById(userId).orElseThrow();
            }
        }
        return null;
    }
}
