package com.geekster.FooDeliveryPlatformApi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Pattern(regexp = "^[A-Z].*", message = "Username must start with a capital letter")
    private String userName;
    @Email
    private String userEmail;
    private String userPassword;
    @Pattern(regexp = "\\+\\d{12}$",message = "Contact should contain + in the starting and your country code and your number!")
    private String userContact;
}
