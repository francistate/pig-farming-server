package com.designtartans.pigfarmingserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmerDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
}
