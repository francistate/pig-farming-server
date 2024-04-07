package com.designtartans.pigfarmingserver.model;

import  jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "drug")
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vetShopId", referencedColumnName = "id", nullable = false)
    private VetShop vetShop;

    private String name;

//    private String type;

    private String administer_method;

    private String dosage;

    private Double price;

    private String description;

}
