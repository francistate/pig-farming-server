package com.designtartans.pigfarmingserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PigDto {
    private Long id;
    private String gender;
    private String breed;
    private Long parentId;
    private LocalDate dateOfBirth;
    private Date dateAdded;
    private Double latestWeight;
    private String pigStatus;
    private Date lastPigStatusUpdate;
    private Long farmId;
}
