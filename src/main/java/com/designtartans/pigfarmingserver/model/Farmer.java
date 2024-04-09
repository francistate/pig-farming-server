package com.designtartans.pigfarmingserver.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "farmer")
public class Farmer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "farm_id", referencedColumnName = "id", nullable = true)
    private Farm farm;

    @OneToOne
    // @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
