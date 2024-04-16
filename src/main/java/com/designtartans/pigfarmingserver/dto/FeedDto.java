package com.designtartans.pigfarmingserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
