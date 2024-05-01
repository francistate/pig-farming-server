package com.designtartans.pigfarmingserver.dto;

import com.designtartans.pigfarmingserver.model.Pig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PigHealthRecordReturnDto {


    private Long id;

    private Pig pig;

    private Long vetId;

    private String description;

    private String treatment;

    private Date date;

}