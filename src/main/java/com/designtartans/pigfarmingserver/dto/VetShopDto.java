package com.designtartans.pigfarmingserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VetShopDto {
    private String vetShopName;

    private String phoneNumber;

    private String email;

    private String district;

    private String province;
}
