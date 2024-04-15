package com.designtartans.pigfarmingserver.dto;

import com.designtartans.pigfarmingserver.model.Farm;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedDto {
    private Long id;
    private String type;
    private Double weight;
    private Double pricePerKg;
    private Double totalPrice;
    private Date dateAdded;
    private Long farmId;
}

