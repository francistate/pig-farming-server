package com.designtartans.pigfarmingserver.dto;

import com.designtartans.pigfarmingserver.model.Pig;
import com.designtartans.pigfarmingserver.model.Vet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthHistoryDto {


    private Long id;

    private Long pigId;

    private Long vetId;

    private String description;

    private String treatment;

    private Date date;
}
