package com.designtartans.pigfarmingserver.dto;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedDto {
    private Long id;
    private String type;
    private Double weight;
    private Double pricePerKg;
    private Double totalPrice;
    private Long farmId;
}
