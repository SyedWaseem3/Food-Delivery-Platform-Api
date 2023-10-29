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
@Table(name = "Admin_Person")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;
    @Pattern(regexp = "^[A-Z].*", message = "First letter must be capital!!")
    private String adminName;
    @Email
    @Pattern(regexp = ".+@admin\\.com", message = "Email must end with @admin.com")
    private String adminEmail;
    private String adminPassword;
    private String adminContact;
}
