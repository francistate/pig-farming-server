package com.designtartans.pigfarmingserver.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "pig_weight_record")
public class PigWeightRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pigId", referencedColumnName = "id", nullable = false)
    private Pig pig;

    private Double weight;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date dateAdded;

}
