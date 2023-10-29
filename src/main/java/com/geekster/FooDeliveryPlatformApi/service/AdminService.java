package com.geekster.FooDeliveryPlatformApi.service;

import com.geekster.FooDeliveryPlatformApi.model.Admin;
import com.geekster.FooDeliveryPlatformApi.model.AuthenticationToken;
import com.geekster.FooDeliveryPlatformApi.repo.IAdminRepo;
import com.geekster.FooDeliveryPlatformApi.service.hashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class AdminService {
    @Autowired
    IAdminRepo adminRepo;

    @Autowired
    AuthenticationService authenticationService;

    public String adminSignUp(Admin newAdmin) {

        String email = newAdmin.getAdminEmail();
        Admin existingAdmin = adminRepo.findFirstByAdminEmail(email);

        if(existingAdmin != null){
            return "Admin email already exists continue with sign in!";
        }

        String password = newAdmin.getAdminPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);
            newAdmin.setAdminPassword(encryptedPassword);
            adminRepo.save(newAdmin);
            return newAdmin.getAdminName() + " admin registered!";
        } catch (NoSuchAlgorithmException e) {
            return "Internal server error, please try again after sometime!";
        }
    }

    public String adminSignIn(String email, String password) {
        Admin existingAdmin = adminRepo.findFirstByAdminEmail(email);

        if(existingAdmin == null){
            return "Invalid email, please sign up first and then try sign in again!";
        }

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            if(existingAdmin.getAdminPassword().equals(encryptedPassword)){
                AuthenticationToken token = new AuthenticationToken(existingAdmin);
                authenticationService.create(token);
                return token.getTokenValue();
            }else{
                return "Invalid credentials!";
            }
        } catch (NoSuchAlgorithmException e) {
            return "Internal server error while saving the password!!";
        }
    }

    public String adminSignOut(String email, String tokenValue) {
        if(authenticationService.authenticateAdmin(email, tokenValue)){
            authenticationService.deleteToken(tokenValue);
            return "Sign out successful!!";
        }else{
            return "Un authenticated access!!";
        }
    }
}
