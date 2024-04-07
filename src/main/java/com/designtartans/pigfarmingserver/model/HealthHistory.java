package com.designtartans.pigfarmingserver.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "healthhistory")
public class HealthHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pigId")
    private Pig pig;

    @ManyToOne
    @JoinColumn(name = "vetId")
    private Vet vet;

    private String description;

    private String treatment;

    private Date date;

}
