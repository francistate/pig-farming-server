package com.designtartans.pigfarmingserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PigDto {
    private String gender;
    private String breed;
    private Long parentId;
    private Date dateOfBirth;
    private Date dateAdded;
    private Double latestWeight;
    private Date lastPigStatusUpdate;
    private Long farmId;
}
