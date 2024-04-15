package com.designtartans.pigfarmingserver.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "pig_health_record")
public class PigHealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pig_id")
    private Pig pig;

    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Vet vet;

    private String description;

    private String treatment;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date date;

}
