package com.designtartans.pigfarmingserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VetDto {
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String password;
    private int vet_shop_id;
}
