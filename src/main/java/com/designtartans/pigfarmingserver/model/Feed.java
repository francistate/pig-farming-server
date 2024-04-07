package com.designtartans.pigfarmingserver.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "feed")
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private Double weight;

    private Double pricePerKg;

    private Double totalPrice;

    private LocalDate dateAdded;

    @ManyToOne
    @JoinColumn(name = "farm_id", referencedColumnName = "id", nullable = false)
    private Farm farm;

}
