package com.designtartans.pigfarmingserver.model;

import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "vetshop")
public class VetShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vetShopName;

    private String phoneNumber;

    private String email;

    private String location;

    private String district;

    private String province;

}
