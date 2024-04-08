package com.designtartans.pigfarmingserver.model;

import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private String role;
}
