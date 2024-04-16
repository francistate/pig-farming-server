package com.designtartans.pigfarmingserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmerDto {
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String password;

}
