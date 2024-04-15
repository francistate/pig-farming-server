package com.designtartans.pigfarmingserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PigHealthRecordDto {


    private Long id;

    private Long pigId;

    private Long vetId;

    private String description;

    private String treatment;

    private Date date;
}
