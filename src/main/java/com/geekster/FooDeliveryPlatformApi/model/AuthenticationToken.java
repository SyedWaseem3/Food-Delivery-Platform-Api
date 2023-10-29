package com.geekster.FooDeliveryPlatformApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Auth_Token")
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationTimeStamp;

    public AuthenticationToken(User user){
        this.user = user;
        this.tokenCreationTimeStamp = LocalDateTime.now();
        this.tokenValue = UUID.randomUUID().toString();
    }

    public AuthenticationToken(Admin admin){
        this.admin = admin;
        this.tokenCreationTimeStamp = LocalDateTime.now();
        this.tokenValue = UUID.randomUUID().toString();
    }

    @OneToOne
            @JoinColumn(name = "fk_user_id")
    User user;

    @OneToOne
            @JoinColumn(name = "fk_admin_id")
    Admin admin;
}
