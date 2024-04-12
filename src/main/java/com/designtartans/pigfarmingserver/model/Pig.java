package com.designtartans.pigfarmingserver.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "pig")
public class Pig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gender;

    private String breed;

    private Long parentId;

    private LocalDate dateOfBirth;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date dateAdded;

    private Double latestWeight;

    private PigStatus pigStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date lastPigStatusUpdate;

    @ManyToOne
    @JoinColumn(name = "farm_id")
    private Farm farm;

}
