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
    private Double latestWeight;
    private String tag;
    private Long farmId;
}
